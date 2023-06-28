package com.example.linkedinclone.main.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Users(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "firstName") val firstName: String,
    @field:Json(name = "lastName") val lastName: String,
    @field:Json(name = "company") val company: Company,
    @field:Json(name = "image") val image: String,
) : Parcelable

/*
@Parcelize
@JsonClass(generateAdapter = true)
data class Users(
    @field:Json(name = "address") val address: Address,
    @field:Json(name = "age") val age: Int,
    @field:Json(name = "bank") val bank: Bank,
    @field:Json(name = "birthDate") val birthDate: String,
    @field:Json(name = "bloodGroup") val bloodGroup: String,
    @field:Json(name = "company") val company: Company,
    @field:Json(name = "domain") val domain: String,
    @field:Json(name = "ein") val ein: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "eyeColor") val eyeColor: String,
    @field:Json(name = "firstName") val firstName: String,
    @field:Json(name = "gender") val gender: String,
    @field:Json(name = "hair") val hair: Hair,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "image") val image: String,
    @field:Json(name = "ip") val ip: String,
    @field:Json(name = "lastName") val lastName: String,
    @field:Json(name = "macAddress") val macAddress: String,
    @field:Json(name = "maidenName") val maidenName: String,
    @field:Json(name = "password") val password: String,
    @field:Json(name = "phone") val phone: String,
    @field:Json(name = "ssn") val ssn: String,
    @field:Json(name = "university") val university: String,
    @field:Json(name = "userAgent") val userAgent: String,
    @field:Json(name = "username") val username: String,
    @field:Json(name = "weight") val weight: Double
) : Parcelable*/
