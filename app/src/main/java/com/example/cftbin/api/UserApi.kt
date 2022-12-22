package com.example.cftbin.api

import com.example.cftbin.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("/{num}")
    suspend fun getUser(@Path("num") num: Int): User
}