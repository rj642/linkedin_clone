package com.example.linkedinclone.main.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Coordinates(
    @field:Json(name = "lat") val lat: Double,
    @field:Json(name = "lng") val lng: Double
) : Parcelable