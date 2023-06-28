package com.example.linkedinclone.common.repository

import com.example.linkedinclone.common.network.ApiClient

class DummyJsonRepo(private val apiClient: ApiClient): BaseRepo() {

    suspend fun getAllPost() = makeRequest {
        apiClient.getAllPosts()
    }

    suspend fun getAllProducts() = makeRequest {
        apiClient.getAllProducts()
    }

    suspend fun getAllUsers() = makeRequest {
        apiClient.getAllUsers()
    }

}