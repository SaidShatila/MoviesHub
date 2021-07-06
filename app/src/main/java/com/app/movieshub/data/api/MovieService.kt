package com.app.movieshub.data.api

import com.app.movieshub.data.entities.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    //https://api.themoviedb.org/3/movie/popular?api_key=d91092f4e07cdcd7c5860125d2b04f81
    //https://api.themoviedb.org/3/movie/581726?api_key=d91092f4e07cdcd7c5860125d2b04f81
    //https://api.themoviedb.org/3/

    @GET("movie/{movie_id}")
   suspend fun getMovieDetails(@Path("movie_id") id: String): Response<Movie>

//    @GET("movie/popular")
//    fun getMovies()

}