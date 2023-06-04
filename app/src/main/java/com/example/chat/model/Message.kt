package com.example.chat.model

data class Message(
    var id:String="",
    var content:String="",
    var time:Long=0,
    var senderId:String="",
    var senderName:String=""
)
