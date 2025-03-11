package com.example.myapplication.SignUp

data class SignUp(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val surname: String = "",
    val name:String = "",
    val patronymic:String = "",
    val dateBirth: String? = null,
    var isEmailError:Boolean = false
)
