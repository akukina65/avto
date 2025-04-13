package com.example.myapplication.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class cartochka(
    val id: String,
    val title: String,
     //Аннотация указывает, что при сериализации/десериализации это свойство должно соответствовать ключу "datebirth" в формате данных (например, JSON)

    val description: String,
    val mesto: String,
    val bonus: String,
    val categoryId: Int,


)