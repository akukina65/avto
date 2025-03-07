package com.example.myapplication.models

import kotlinx.serialization.Serializable

@Serializable
data class Users(
    val id: Int,
    val email: String,
    val password: String,
)
@Serializable
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val email: String,
    val password: String,
    val birthDate: String
)