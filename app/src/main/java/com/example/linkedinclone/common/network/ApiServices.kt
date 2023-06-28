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

    @GET("users?skip=12&limit=50")
    suspend fun getAllUsers(): Response<UserModel>

}