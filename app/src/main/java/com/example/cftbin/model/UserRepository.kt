package com.example.cftbin.model

import com.example.cftbin.api.UserApi
import com.example.cftbin.model.room.UserDAO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://lookup.binlist.net/"

class UserRepository(private val dao: UserDAO) {
    private val userApi: UserApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(UserApi::class.java)
    }

    suspend fun getUser(binNum: Int): User {
        return userApi.getUser(binNum)
    }

    suspend fun saveUser(user: User) {
        dao.insertUser(user)
    }

    suspend fun getUsers(): List<User> {
        return dao.getAll()
    }
}