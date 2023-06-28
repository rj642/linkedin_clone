package com.example.linkedinclone.main.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Company(
    @field:Json(name = "address") val address: Address,
    @field:Json(name = "department") val department: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "title") val title: String
) : Parcelable