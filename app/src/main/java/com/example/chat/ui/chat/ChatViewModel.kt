package com.example.chat.ui.chat

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.chat.model.Room
import com.example.chat.model.Message
import com.example.chat.ui.base.BaseViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ChatViewModel : BaseViewModel() {
    var navegator: chatNavegator? = null
    var messageLiveData = MutableLiveData<MutableList<Message>>()
    lateinit var room: Room

    fun subscribeToMessageCollection() {
        Firebase.firestore.collection("rooms").document(room.id)
            .collection("messages").orderBy("time").addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.e("subscribe", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.documents.isEmpty()) {
                    var messageList: MutableList<Message> = mutableListOf()
                    snapshot.documents.forEach {
                        messageList.add(it.toObject()!!)
                    }
                    messageLiveData.value = messageList
                    //  Log.d("subscribe", "$source data: ${snapshot.data}")
                } else {
                    Log.e("subscribe", "current data: null")
                }
            }


    }

    fun sendMessage(message: Message) {

        val emptyDoc = Firebase.firestore.collection("rooms")
            .document(room.id).collection("messages")
            .document()
        message.id = emptyDoc.id
        emptyDoc.set(message).addOnSuccessListener {
            navegator?.onSendMessage()

        }

    }
}