package com.app.movieshub.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView?, imageUrl: String?) {
    if (imageView != null && imageUrl != null) {
        Glide.with(imageView).load(imageUrl).into(imageView)
    }
}
