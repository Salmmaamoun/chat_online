package com.example.chat.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityRegBinding
import com.example.chat.ui.base.BaseActivity

class RegActivity : BaseActivity<RegisterViewModel,ActivityRegBinding>(),RegisterNavigator{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel
        viewModel.navigator=this


    }

    override fun getLayoutId(): Int =R.layout.activity_reg
    override fun initViewModel(): RegisterViewModel {
        return ViewModelProvider(this).get(RegisterViewModel::class.java)
    }


    override fun goBackLoginScreen() {
        finish()
    }


}
