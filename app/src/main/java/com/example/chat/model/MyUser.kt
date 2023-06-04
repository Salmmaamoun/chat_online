package com.example.chat.model

data class MyUser(
    var id:String="",
    var firstName:String="",
    var lastName:String="",
    var email:String=""
){
    companion object{
        var currentUser:MyUser?=null
    }
}
