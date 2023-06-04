package com.example.chat.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityLoginBinding
import com.example.chat.ui.base.BaseActivity
import com.example.chat.ui.home.HomeActivity
import com.example.chat.ui.register.RegActivity


class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>(),LoginNav {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        initLisener()
        viewModel.navigator=this

    }

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    fun initLisener(){
        viewBinding.creatAccountTv.setOnClickListener {
            var intent=Intent(this@LoginActivity,RegActivity::class.java)
            startActivity(intent)
        }

    }
    fun observeViewModewl() {
        viewModel.isLoadingLiveData.observe(this) {
            if(it){
                showLodingDialog()

            }else{
                hideLoding()

            }

        }
        viewModel.dialogMessageLiveData.observe(this){
            showMessage(it,"ok"
            )
        }
    }

    override fun navigatorToHome() {
        var intent=Intent(this@LoginActivity,HomeActivity::class.java)
        startActivity(intent)
    }

}