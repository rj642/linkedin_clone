package com.example.linkedinclone.common.network

import com.example.linkedinclone.BuildConfig
import retrofit2.Response

open class BaseApiClient {

    protected suspend fun <T> getResult(request: suspend () -> Response<T>): Resource<T> {
        try {
            val response = request()
            return if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Resource.success(body, response.code())
                } else {
                    Resource.error(
                        message = "Error in getting the response from api",
                        code = response.code()
                    )
                }
            } else {
                Resource.error(message = "Something went wrong", code = response.code())
            }
        } catch (e: Exception) {
            val message = e.message ?: e.toString()
            return if (BuildConfig.DEBUG) {
                Resource.error(message = "Network call failed due to $message", code = 404)
            } else {
                Resource.error(message = "No internet connectivity", code = 404)
            }
        }
    }

}