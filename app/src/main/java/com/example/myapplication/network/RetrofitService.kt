package com.example.myapplication.network

import com.example.myapplication.models.ResponsResult
import com.example.myapplication.models.SignUp
import com.example.myapplication.models.Users
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    @POST("/users/auth/signup")
    fun signUp(
        @Body signUp: SignUp
    ):Call<ResponsResult>

    @GET("/users")
    fun getUsers(): Call<Users>
}