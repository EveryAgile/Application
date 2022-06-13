package com.example.myapplication.models

data class CreateSprint(
    var description: String,
    var endTime: String,
    var importance: String,
    var projectId: Int,
    var sprintName: String,
    var users: List<String>
)
