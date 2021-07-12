package com.app.movieshub.movie

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieshub.data.api.MovieRepository
import com.app.movieshub.data.api.POSTER_BASE_URL
import com.app.movieshub.data.entities.Movie
import com.app.movieshub.utils.ErrorViewModel
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

data class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieId: String,

    ) : ViewModel() {
    val contentVisibility = ObservableInt(View.GONE)
    val errorVisibility = ObservableInt(View.GONE)
    val loadingVisibility = ObservableInt(View.GONE)
    val contentViewModel = ObservableField<MovieDetailsStateViewModel.Content>()
    val errorViewModel = ObservableField<ErrorViewModel>()

    private var state: Any by Delegates.observable(MovieDetailsStateViewModel.Loading) { _, oldValue, newValue ->
        when (newValue) {
            is MovieDetailsStateViewModel.Content -> {
                contentVisibility.set(View.VISIBLE)
                errorVisibility.set(View.INVISIBLE)
                loadingVisibility.set(View.GONE)
                contentViewModel.set(newValue)
            }
            is ErrorViewModel -> {
                contentVisibility.set(View.GONE)
                errorVisibility.set(View.VISIBLE)
                loadingVisibility.set(View.GONE)
                errorViewModel.set(newValue)
            }
            MovieDetailsStateViewModel.Loading -> {
                contentVisibility.set(View.GONE)
                errorVisibility.set(View.GONE)
                loadingVisibility.set(View.VISIBLE)
            }
        }
    }

    init {
        fetchMovieDetails()
    }

    fun fetchMovieDetails() {
        viewModelScope.launch {
            state = MovieDetailsStateViewModel.Loading
            val result: Result<Movie?> = movieRepository.fetchMovieDetails(movieId)
            if (result.isSuccess) {
                result.getOrNull()?.let { movie ->
                    state = MovieDetailsStateViewModel.Content(movie)
                } ?: run {
                    state = ErrorViewModel(
                        result.exceptionOrNull()?.message ?: "Please try again",
                        retryAction = {
                            fetchMovieDetails()
                        })
                }

            } else {
                state = ErrorViewModel(
                    result.exceptionOrNull()?.message ?: "Unknown error",
                    retryAction = {
                        fetchMovieDetails()
                    }
                )
            }
        }
    }
}


sealed class MovieDetailsStateViewModel {
    data class Content(val movie: Movie) : MovieDetailsStateViewModel() {
        val releaseDateString = movie.releaseDate.toString()
        val runtimeString = movie.runtime.toString()
        val revenueString = movie.revenue.toString()
        val ratingString = movie.rating.toString()
        val budgetString = movie.budget.toString()
        val moviePosterPath = POSTER_BASE_URL + movie.posterPath
    }
    object Loading : MovieDetailsStateViewModel()
}