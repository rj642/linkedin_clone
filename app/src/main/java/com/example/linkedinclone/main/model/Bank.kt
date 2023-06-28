package com.example.linkedinclone.main.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Bank(
    @field:Json(name = "cardExpire") val cardExpire: String,
    @field:Json(name = "cardNumber") val cardNumber: String,
    @field:Json(name = "cardType") val cardType: String,
    @field:Json(name = "currency") val currency: String,
    @field:Json(name = "iban") val iban: String
) : Parcelable