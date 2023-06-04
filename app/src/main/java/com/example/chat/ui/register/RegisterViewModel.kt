package com.example.chat.ui.register

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.chat.model.MyUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class RegisterViewModel : ViewModel() {
    var firstName: String = ""
    var firstNameError = ObservableField<String>()
    var email: String = ""
    var emailError = ObservableField<String>()
    var password: String = ""
    var passError = ObservableField<String>()
    var lastName: String = ""
    var lastNameError = ObservableField<String>()
    var navigator: RegisterNavigator? = null

    var auth: FirebaseAuth = Firebase.auth

    fun creatAccount() {
        if (!validate()) return;
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                it.result.user?.let { it1 -> creatUserInFireStore(it1.uid) }
                it.result.user?.uid?.let { it1 -> Log.e("succecful", it1) }

            } else {
                it.exception?.message?.let { it1 -> Log.e("error", it1) }

            }
        }

        Log.e("id", "$firstName")
        Log.e("id", "$lastName")
        Log.e("id", "$password")
        Log.e("id", "$email")
    }

    fun creatUserInFireStore(uid: String) {
        val emptyDoc = Firebase.firestore.collection("users")
            .document(uid)
        val user = MyUser(uid, firstName, lastName, email)
        emptyDoc.set(user).addOnSuccessListener {
            navigator?.goBackLoginScreen()

        }

    }

    fun validate(): Boolean {
        var isValid = true
        if (firstName.isEmpty()) {
            firstNameError.set("please valid first name")
            isValid = false

        } else {
            firstNameError.set(null)
            isValid = true
        }

        if (lastName.isEmpty()) {
            lastNameError.set("please valid last name")
            isValid = false

        } else {
            lastNameError.set(null)
            isValid = true
        }
        if (email.isEmpty()) {
            emailError.set("please valid last name")
            isValid = false

        } else {
            emailError.set(null)
            isValid = true
        }
        if (password.isEmpty()) {
            passError.set("please valid last name")
            isValid = false

        } else {
            passError.set(null)
            isValid = true
        }
        return isValid
    }
}