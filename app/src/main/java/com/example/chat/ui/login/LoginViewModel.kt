package com.example.chat.ui.login

import android.util.Log
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chat.model.MyUser
import com.example.chat.ui.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class LoginViewModel : BaseViewModel() {
    var email: String = ""
    var emailError = ObservableField<String>()
    var password: String = ""
    var passError = ObservableField<String>()
    var auth: FirebaseAuth = Firebase.auth
    var navigator:LoginNav?=null

    fun login() {
        Log.e("id", "$password")
        Log.e("id", "$email")
        if (!validate()) return
        isLoadingLiveData.value = true
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            isLoadingLiveData.value=false
            if (it.isSuccessful) {
                getUserFromFireStore(it.result.user!!.uid)
                navigator?.navigatorToHome()
              //  dialogMessageLiveData.value="Successful login"
                it.result.user?.uid?.let { it1 -> Log.e("succecful", it1) }

            } else {
                dialogMessageLiveData.value=it.exception?.message
                it.exception?.message?.let { it1 -> Log.e("error", it1) }

            }

        }
    }
    fun getUserFromFireStore(uid:String){
        Firebase.firestore.collection("users").document(uid).
                get().addOnSuccessListener {
                  MyUser.currentUser=  it.toObject<MyUser>()
        }
    }

    fun validate(): Boolean {
        var isValid = true
        if (email.isNullOrBlank()) {
            emailError.set("please valid last name")
            isValid = false

        } else {
            emailError.set(null)
            isValid = true
        }
        if (password.isNullOrBlank()) {
            passError.set("please valid last name")
            isValid = false

        } else {
            passError.set(null)
            isValid = true
        }
        return isValid
    }



}