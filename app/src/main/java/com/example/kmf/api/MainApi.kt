package com.example.kmf.api

import com.example.kmf.data.User
import retrofit2.http.*

interface MainApi {
    @POST("user")
    suspend fun auth(@Body user: User): Result<Any>

    @GET("user/{username}")
    suspend fun getUser(@Path("username") username: String): User
}