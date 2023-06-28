package com.example.linkedinclone.common.network

data class Resource<out T>(val status: Status, val data: T?, val message: String?, val code: Int) {

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T, code: Int): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null,
                code
            )
        }

        fun <T> error(data: T? = null, message: String, code: Int): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                message,
                code
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null,
                0
            )
        }
    }

}
