package com.app.movieshub.data.entities

import com.app.movieshub.utils.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Movie(
    @SerialName("budget") val budget: Int,
    @SerialName("id") val id: Int,
    @SerialName("overview") val overview: String,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("release_date") @Serializable(DateSerializer::class) val releaseDate: Date,
    @SerialName("revenue") val revenue: Int,
    @SerialName("runtime") val runtime: Int,
    @SerialName("status") val status: String,
    @SerialName("tagline") val tagline: String,
    @SerialName("title") val title: String,
    @SerialName("video") val video: Boolean,
    @SerialName("vote_average") val rating: Double,
)