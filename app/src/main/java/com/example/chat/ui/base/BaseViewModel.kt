package com.example.chat.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel:ViewModel() {
    var isLoadingLiveData= MutableLiveData<Boolean>()
    var dialogMessageLiveData=MutableLiveData<String>()
}