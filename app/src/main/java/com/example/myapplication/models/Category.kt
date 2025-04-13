package com.example.myapplication.models

import kotlinx.serialization.Serializable

@Serializable // Указывает компилятору, что этот класс может быть сериализован и десериализован
data class Category(
    val id: Int,
    val name: String
)