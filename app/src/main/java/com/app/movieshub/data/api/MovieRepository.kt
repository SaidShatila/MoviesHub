package com.app.movieshub.data.api

import com.app.movieshub.data.entities.Movie

class MovieRepository(private val movieService: MovieService) {

    suspend fun fetchMovieDetails(movieId:String): Result<Movie?> {
       val response =  movieService.getMovieDetails(id = movieId)

        val result:Result<Movie?> = if(response.isSuccessful){
            Result.success(response.body())
        }else Result.failure(Throwable())


        return result
    }
}