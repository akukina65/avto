package com.example.myapplication.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Users(
    val id: Int,
    val email: String,
    val password: String,
)
@Serializable
data class Profile(

    val surname: String,
    val name: String,
    val patronymic: String,
    @SerialName("datebirth") //Аннотация указывает, что при сериализации/десериализации это свойство должно соответствовать ключу "datebirth" в формате данных (например, JSON)
    val dateBirth:String?,
    val image:String?
)