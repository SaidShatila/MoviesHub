package com.app.movieshub.data.api

import com.app.movieshub.DummyDependencyProvider.movieService
import com.app.movieshub.data.entities.Movie

class MoviesRepository {

    suspend fun fetchMovieDetails(movieId:String): Result<Movie?> {
        val response =  movieService.getMovies()

        val result:Result<Movie?> = if(response.isSuccessful){
            Result.success(response.body())
        }else Result.failure(Throwable())


        return result
    }
}