package com.example.myapplication.models

data class SignUp(
    val email: String,
    val name: String,
    val password: String
)

data class SignIn(
    val email: String,
    val password: String
)

data class Reissue(
    val accessToken: String,
    val refreshToken: String
)