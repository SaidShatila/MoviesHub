package com.app.movieshub.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieshub.data.api.MovieRepository
import com.app.movieshub.data.entities.Movie
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieId: String
) : ViewModel() {

    val movieName: String = "hellloy"

    init {
        viewModelScope.launch {
            val result: Result<Movie?> = movieRepository.fetchMovieDetails(movieId)
            if (result.isSuccess) {
                result.getOrNull()?.let { movie ->

                }
            } else {
                //switch to error state
            }
        }
    }
}