package com.app.movieshub.data.entities

import com.app.movieshub.utils.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Movie(
    @SerialName("id") val id: Int,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path") val posterPath: String,
    @SerialName("release_date") @Serializable(DateSerializer::class) val releaseDate: Date,
    val revenue: Int,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average") val rating: Double,
)