package com.app.movieshub

import com.app.movieshub.data.api.BASE_URL
import com.app.movieshub.data.api.MovieRepository
import com.app.movieshub.data.api.MovieService
import com.app.movieshub.data.api.requestInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object DummyDependencyProvider {
    val httpClient: OkHttpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor).build()
    val movieService: MovieService =
        Retrofit.Builder().client(httpClient).baseUrl(BASE_URL)
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(MovieService::class.java)
    val movieRepository: MovieRepository = MovieRepository(movieService)
}
