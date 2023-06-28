package com.example.linkedinclone.common.network

import com.example.linkedinclone.main.model.PostSchema
import com.example.linkedinclone.main.model.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {

    @GET("posts")
    suspend fun getAllPosts(): Response<PostSchema>

    @GET("products")
    suspend fun getAllProducts(): Response<Map<String, Any>>

    @GET("users?limit=11")
    suspend fun getAllUsers(): Response<UserModel>

}