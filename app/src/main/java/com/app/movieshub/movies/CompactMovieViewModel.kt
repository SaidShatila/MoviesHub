package com.app.movieshub.movies

import androidx.lifecycle.ViewModel
import com.app.movieshub.data.api.POSTER_BASE_URL
import com.app.movieshub.data.entities.CompactMovie
import com.app.movieshub.data.entities.Movie


data class CompactMoviesViewModel(
    val movie: CompactMovie,
    val onClick: (CompactMovie) -> Unit
) : ViewModel() {

    val releaseDateString = movie.releaseDate
    val imagePoster = POSTER_BASE_URL + movie.posterPath

    fun onMovieClicked(){
        onClick(movie)
    }
}