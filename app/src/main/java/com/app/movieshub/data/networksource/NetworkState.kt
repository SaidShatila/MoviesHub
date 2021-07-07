package com.app.movieshub.data.networksource

import com.app.movieshub.data.entities.Movie


sealed class NetworkState {
    data class Success(val data: Movie) : NetworkState()
    data class Error(val errorMsg: String) : NetworkState()
    object Loading : NetworkState()


}