package com.app.movieshub.utils

import androidx.lifecycle.ViewModel

data class ErrorViewModel(
    val errorMSG: String,
    val retryAction: () -> Unit

) : ViewModel(

) {


}

