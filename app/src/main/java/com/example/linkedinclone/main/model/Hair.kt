package com.example.linkedinclone.main.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Hair(
    @field:Json(name = "color") val color: String,
    @field:Json(name = "type") val type: String
) : Parcelable