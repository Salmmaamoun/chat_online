package com.example.chat.model

import java.io.Serializable

data class Room(
    var id: String = "",
    var tittle: String? = "",
    var describtion: String? = "",
    var roomCategory: String? = ""
):Serializable