package com.example.myapplication.state

import kotlinx.serialization.SerialName


data class cartochkaState (
    var id :String = "",
    var title:String = "",
    var category:Int = -1,
    val description: String = "",

    val mesto:String  = "",
    val bonus: String = "",

)

