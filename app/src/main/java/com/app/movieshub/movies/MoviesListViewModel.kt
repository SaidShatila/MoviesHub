package com.app.movieshub.movies

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieshub.data.api.MovieRepository
import com.app.movieshub.data.entities.CompactMovie
import com.app.movieshub.data.entities.MoviesResponse
import com.app.movieshub.utils.ErrorViewModel
import com.app.movieshub.utils.MoviesListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

data class MoviesListViewModel(
    private val movieRepository: MovieRepository,
    private val page: Int,
    private val onMovieClicked: (CompactMovie) -> Unit
) : ViewModel() {
    val contentVisibility = ObservableInt(View.GONE)
    val errorVisibility = ObservableInt(View.GONE)
    val loadingVisibility = ObservableInt(View.GONE)
    val contentViewModel = ObservableField<MoviesListStateViewModel.Content>()
    val errorViewModel = ObservableField<ErrorViewModel>()

    private var state: Any by Delegates.observable(MoviesListStateViewModel.Loading) { _, oldValue, newValue ->
        when (newValue) {
            is MoviesListStateViewModel.Content -> {
                contentVisibility.set(View.VISIBLE)
                errorVisibility.set(View.GONE)
                loadingVisibility.set(View.GONE)
                contentViewModel.set(newValue)
            }
            is ErrorViewModel -> {
                contentVisibility.set(View.GONE)
                errorVisibility.set(View.VISIBLE)
                loadingVisibility.set(View.GONE)
                errorViewModel.set(newValue)
            }
            MoviesListStateViewModel.Loading -> {
                contentVisibility.set(View.GONE)
                errorVisibility.set(View.GONE)
                loadingVisibility.set(View.VISIBLE)
            }
        }
        Log.e(
            "Said",
            "$state errorVisibility: ${errorVisibility.get() == View.VISIBLE}, contentVisibility: ${contentVisibility.get() == View.VISIBLE}"
        )
    }

    init {
        fetchMovies()
    }


    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            state = MoviesListStateViewModel.Loading
            val result: Result<MoviesResponse?> = movieRepository.fetchMovies(page = 1)
            if (result.isSuccess) {
                result.getOrNull()?.let { movies ->
                    state = MoviesListStateViewModel.Content(movies, onMovieClicked)
                } ?: run {
                    state = ErrorViewModel(
                        result.exceptionOrNull()?.message ?: "Please try again",
                        retryAction = {
                            fetchMovies()
                        })
                }

            } else {
                state = ErrorViewModel(
                    result.exceptionOrNull()?.message ?: "Unknown error",
                    retryAction = {
                        fetchMovies()
                    }
                )
            }
        }
    }


    sealed class MoviesListStateViewModel {
        data class Content(
            val moviesResponse: MoviesResponse,
            val onClick: (CompactMovie) -> Unit
        ) :
            MoviesListStateViewModel() {
            val adapter = MoviesListAdapter(moviesResponse.movies, onClick)
        }

        object Loading : MoviesListStateViewModel()
    }
}
