package com.example.chat.ui.utlitize

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.chat.R
import com.google.android.material.textfield.TextInputLayout
@BindingAdapter("error")
fun addTextInputError(textInputLaout:TextInputLayout,error:String?){
    textInputLaout.error=error

}
@BindingAdapter("loadImgFromDrawable")
fun loadImgFromDrawable(img:ImageView,roomCategory:String){
    if(roomCategory=="Music"){
        img.setImageResource(R.drawable.music)

    }else if(roomCategory=="Sports"){
         img.setImageResource(R.drawable.sports)
    }else{
         img.setImageResource(R.drawable.movies)

    }

}