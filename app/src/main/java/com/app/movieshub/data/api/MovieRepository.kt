package com.app.movieshub.data.api

import com.app.movieshub.data.entities.Movie
import com.app.movieshub.data.entities.MoviesResponse

class MovieRepository(private val movieService: MovieService) {

    suspend fun fetchMovieDetails(movieId: String): Result<Movie?> {
        try {
            val response = movieService.getMovieDetails(id = movieId)

            val result: Result<Movie?> = if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Throwable())
            }
            return result
        } catch (e: Exception) {
            return Result.failure(e)

        }
    }

    suspend fun fetchMovies(page: Int): Result<MoviesResponse?> {

        try {
            val response = movieService.getMovies(page)

            val result: Result<MoviesResponse?> = if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(Throwable())
            }
            return result
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}