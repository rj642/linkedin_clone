package com.example.linkedinclone.common.repository

import com.example.linkedinclone.common.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class BaseRepo {

    protected suspend fun<T> makeRequest(
        request: suspend () -> Resource<T>
    ) = flow<Resource<T>> {
        emit(Resource.loading())

        val response = request.invoke()

        when (response.status) {
            Resource.Status.ERROR -> {
                emit(Resource.error(message = "Something went wrong", code = response.code))
            }
            Resource.Status.SUCCESS -> {
                emit(if (response.data != null) Resource.success(response.data, code = response.code) else Resource.error(message = "Something went wrong", code = response.code))
            }
            else -> {

            }
        }

    }.flowOn(Dispatchers.IO)

}