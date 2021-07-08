package com.app.movieshub.movie

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieshub.data.api.MovieRepository
import com.app.movieshub.data.entities.Movie
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

data class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieId: String
) : ViewModel() {
    val contentVisibility = ObservableInt(View.GONE)
    val errorVisibility = ObservableInt(View.GONE)
    val contentViewModel = ObservableField<StateViewModel.Content>()
    val errorViewModel = ObservableField<StateViewModel.Error>()

    private var state: StateViewModel by Delegates.observable(StateViewModel.Loading) { _, oldValue, newValue ->
        when (newValue) {
            is StateViewModel.Content -> {
                contentVisibility.set(View.VISIBLE)
                errorVisibility.set(View.GONE)
                contentViewModel.set(newValue)
            }
            is StateViewModel.Error -> {
                contentVisibility.set(View.GONE)
                errorVisibility.set(View.VISIBLE)
                errorViewModel.set(newValue)
            }
            StateViewModel.Loading -> {
                contentVisibility.set(View.GONE)
                errorVisibility.set(View.GONE)
            }
        }
    }


    init {
        viewModelScope.launch {
            state = StateViewModel.Loading
            val result: Result<Movie?> = movieRepository.fetchMovieDetails(movieId)
            if (result.isSuccess) {
                result.getOrNull()?.let { movie ->
                    state = StateViewModel.Content(movie)
                } ?: run {
                    state =
                        StateViewModel.Error(IllegalStateException("The content of the movie is unavailable"))
                }
            } else {
                state = StateViewModel.Error(
                    result.exceptionOrNull() ?: IllegalStateException("Unknown error")
                )
            }
        }
    }
}

sealed class StateViewModel {
    data class Content(val movie: Movie) : StateViewModel() {
        val releaseDateString = movie.releaseDate.toString()
        val runtimeString = movie.runtime.toString()
        val revenueString = movie.revenue.toString()
        val ratingString = movie.rating.toString()
        val budgetString = movie.budget.toString()

    }

    object Loading : StateViewModel()
    data class Error(val throwable: Throwable) : StateViewModel()
}