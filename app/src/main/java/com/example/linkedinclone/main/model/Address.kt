package com.example.linkedinclone.main.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Address(
    @field:Json(name = "address") val address: String,
    @field:Json(name = "city") val city: String,
    @field:Json(name = "coordinates") val coordinates: Coordinates,
    @field:Json(name = "postalCode") val postalCode: String,
    @field:Json(name = "state") val state: String
) : Parcelable