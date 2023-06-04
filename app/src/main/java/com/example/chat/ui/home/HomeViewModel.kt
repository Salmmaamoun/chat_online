package com.example.chat.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.chat.model.Room
import com.example.chat.ui.base.BaseViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HomeViewModel:BaseViewModel() {
    val roomLiveData=MutableLiveData<MutableList<Room>>()
    fun getRooms(){
        //isLoadingLiveData.value=true
        Firebase.firestore.collection("rooms").get().addOnSuccessListener {
            Log.e("succes","salam")
            var rooms:MutableList<Room> = mutableListOf()
            it.documents.forEach {doc->
                rooms.add(doc.toObject()!!)

            }
            isLoadingLiveData.value=false
            roomLiveData.value=rooms

        }.addOnFailureListener{
            Log.e("error",it.message.toString())
           // isLoadingLiveData.value=false
            //dialogMessageLiveData.value="some thing wrong"

        }
    }
}