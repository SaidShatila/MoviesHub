package com.app.movieshub.movie

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieshub.data.api.MovieRepository
import com.app.movieshub.data.api.POSTER_BASE_URL
import com.app.movieshub.data.entities.Movie
import kotlinx.coroutines.launch
import kotlin.properties.Delegates
import com.bumptech.glide.Glide

data class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieId: String,

) : ViewModel() {
    val contentVisibility = ObservableInt(View.GONE)
    val errorVisibility = ObservableInt(View.GONE)
    val contentViewModel = ObservableField<MovieDetailsStateViewModel.Content>()
    val errorViewModel = ObservableField<MovieDetailsStateViewModel.Error>()

    private var state: MovieDetailsStateViewModel by Delegates.observable(MovieDetailsStateViewModel.Loading) { _, oldValue, newValue ->
        when (newValue) {
            is MovieDetailsStateViewModel.Content -> {
                contentVisibility.set(View.VISIBLE)
                errorVisibility.set(View.INVISIBLE)
                contentViewModel.set(newValue)
            }
            is MovieDetailsStateViewModel.Error -> {
                contentVisibility.set(View.GONE)
                errorVisibility.set(View.VISIBLE)
                errorViewModel.set(newValue)
            }
            MovieDetailsStateViewModel.Loading -> {
                contentVisibility.set(View.GONE)
                errorVisibility.set(View.GONE)
            }
        }
    }


    init {
        viewModelScope.launch {
            state = MovieDetailsStateViewModel.Loading
            val result: Result<Movie?> = movieRepository.fetchMovieDetails(movieId)
            if (result.isSuccess) {
                result.getOrNull()?.let { movie ->
                    state = MovieDetailsStateViewModel.Content(movie)
                } ?: run {
                    state =
                        MovieDetailsStateViewModel.Error(IllegalStateException("The content of the movie is unavailable"))
                }
            } else {
                state = MovieDetailsStateViewModel.Error(
                    result.exceptionOrNull() ?: IllegalStateException("Unknown error")
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
    data class Error(val throwable: Throwable) : MovieDetailsStateViewModel()

}