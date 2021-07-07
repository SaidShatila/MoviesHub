package com.app.movieshub.movie

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.movieshub.data.api.MovieRepository
import com.app.movieshub.data.api.MovieService
import com.app.movieshub.data.entities.Movie
import com.app.movieshub.data.networksource.NetworkState
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieRepository: MovieRepository,
    private val movieId: String
) : ViewModel() {

    val movieName: String = "Movie"
    val movie

    init {
        viewModelScope.launch {
            val result: Result<Movie?> = movieRepository.fetchMovieDetails(movieId)
            if (result.isSuccess) {
                result.getOrNull()?.let { movie ->
                    when (movie){
                        is NetworkState.Success ->{
                            // we add layout and success
                        }
                        is NetworkState.Error ->{
                            // we set the visibilty till gone
                        }
                        is NetworkState.Loading ->{
                            // we set the progress bar to visible
                        }
                    }
                }
            } else {
                result.isFailure
                //switch to error state
            }
        }
    }
}