package com.app.movieshub.data.api

import okhttp3.Interceptor

const val API_KEY = "api_key"
const val API_KEY_VALUE = "d91092f4e07cdcd7c5860125d2b04f81"
const val BASE_URL = "https://api.themoviedb.org/3/"

const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"


val requestInterceptor = Interceptor { chain ->
    val url = chain.request()
        .url
        .newBuilder()
        .addQueryParameter(API_KEY, API_KEY_VALUE)
        .build()

    val request = chain.request()
        .newBuilder()
        .url(url)
        .build()

    return@Interceptor chain.proceed(request)
}
