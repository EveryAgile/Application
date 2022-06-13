package com.example.myapplication.models

data class Projects (
    val endTime: String,
    val projectName: String,
    val startTime: String,
    val type: String
)

data class Member(
    val memberEmail: String,
    val projectId: Int
)