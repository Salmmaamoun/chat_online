package com.example.chat.ui.add_room

import android.util.Log
import com.example.chat.model.Room
import com.example.chat.ui.base.BaseViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddRoomViewModel:BaseViewModel() {
    var navigator:AddRoomNav?=null
    fun creatRoom(room: Room){
      //  isLoadingLiveData.value=true
       var emptyDoc= Firebase.firestore.collection("rooms").document()
        room.id=emptyDoc.id

        emptyDoc.set(room).addOnSuccessListener {
           // isLoadingLiveData.value=false
            Log.e("sucess","salam")
            navigator?.navigatorToHome()
        }.addOnFailureListener {
            Log.e("error","try again")
        }


    }
}