package com.example.myapplication.SignIn

data class SignInState(



val email: String = "",
val password: String = "",
var errorEmail:Boolean = false,
val errorPassword:Boolean = false
)

