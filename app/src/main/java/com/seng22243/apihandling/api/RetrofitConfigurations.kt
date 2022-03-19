package com.seng22243.apihandling.api

import com.seng22243.apihandling.api.UserService.Companion.API_URL
import com.seng22243.apihandling.model.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface UserService {
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{userId}")
    fun getUser(@Path("userId") id: String): Call<User>

    companion object {
        val API_URL = "https://jsonplaceholder.typicode.com"
        fun create(): UserService {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UserService::class.java)
        }
    }
}

class RetrofitConfigurations {
    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val userService = retrofit.create(UserService::class.java)
    }
}