package com.example.linkedinclone.main.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserModel(
    @field:Json(name = "users") val users: List<Users>,
    @field:Json(name = "total") val total: Int,
    @field:Json(name = "skip") val skip: Int,
    @field:Json(name = "limit") val limit: Int
) : Parcelable
