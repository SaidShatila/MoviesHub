package com.app.movieshub.data.networksource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.movieshub.data.api.MovieService
import com.app.movieshub.data.entities.Movie
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.ParentJob

@InternalCoroutinesApi
class MovieNetworkSource (private val apiService : MovieService, private val parentJob: ParentJob) {
        private val _networkState = MutableLiveData<NetworkState>()
        val networkState: LiveData<NetworkState>
        get() = _networkState

        private val _downloadMovieResponse = MutableLiveData<Movie>()
        val downloadMovieResponse: LiveData<Movie>
                get() = _downloadMovieResponse

        fun fetchMovie(movieId:Int){
//               (when(networkState){
//                        is NetworkState.Loading -> {
//                                loading(networkState.loading)
//                        }
//                })
        }
}