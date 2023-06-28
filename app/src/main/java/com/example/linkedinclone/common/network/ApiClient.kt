package com.example.linkedinclone.common.network

import javax.inject.Inject

class ApiClient(private val apiServices: ApiServices): BaseApiClient() {

    suspend fun getAllPosts() = getResult {
        apiServices.getAllPosts()
    }

    suspend fun getAllProducts() = getResult {
        apiServices.getAllProducts()
    }

    suspend fun getAllUsers() = getResult {
        apiServices.getAllUsers()
    }

}