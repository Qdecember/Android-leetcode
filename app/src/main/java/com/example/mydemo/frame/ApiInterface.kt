package com.example.mydemo.frame

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {


    @GET("users/{user}/repos")
    open fun listRepos(@Path("user") user: String?): Call<MutableList<String>>

    @GET("user")
    open fun getUser(@Path("userId") userId: String): Call<User>
}