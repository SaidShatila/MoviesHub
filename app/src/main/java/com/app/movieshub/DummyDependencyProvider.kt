package com.app.movieshub

import com.app.movieshub.data.api.BASE_URL
import com.app.movieshub.data.api.MovieRepository
import com.app.movieshub.data.api.MovieService
import com.app.movieshub.data.api.requestInterceptor
import com.app.movieshub.utils.UnsafeOkHttpClient
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object DummyDependencyProvider {
    private val httpClient: OkHttpClient = UnsafeOkHttpClient
        .getUnsafeOkHttpClient()
        .addInterceptor(requestInterceptor).build()
    private val retrofit = Retrofit.Builder().client(httpClient).baseUrl(BASE_URL)
        .addConverterFactory(
            Json {
                ignoreUnknownKeys = true
            }.asConverterFactory("application/json".toMediaType())
        )
        .build()
    val movieService: MovieService = retrofit.create(MovieService::class.java)
    val movieRepository: MovieRepository = MovieRepository(movieService)
}
