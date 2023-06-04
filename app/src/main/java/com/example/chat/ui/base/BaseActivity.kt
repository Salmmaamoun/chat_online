package com.example.chat.ui.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityRegBinding
import com.example.chat.ui.register.RegisterViewModel

open abstract class BaseActivity<VM:ViewModel,B:ViewDataBinding>:AppCompatActivity() {
    lateinit var  viewBinding: B
    lateinit var viewModel: VM
    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= DataBindingUtil.setContentView(this, getLayoutId())
        viewModel= initViewModel()

    }
    abstract fun getLayoutId():Int
    abstract fun initViewModel():VM
    fun showLodingDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loding...")
        progressDialog?.show()
    }

    fun hideLoding() {
        progressDialog?.dismiss()
    }

    var alertDialog: AlertDialog? = null
    fun showMessage(
        message: String,
        posActiontitle: String? = null,
        posAction: DialogInterface.OnClickListener? = null,
        negActionTitle: String? = null,
        negAction: DialogInterface.OnClickListener? = null,
        canable: Boolean = true


    ) {
        var messageDialodBuilder = AlertDialog.Builder(this)
        messageDialodBuilder.setMessage(message)
        if (posActiontitle != null) {
            messageDialodBuilder.setPositiveButton(posActiontitle,
                posAction ?: DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                }

            )

        }
        if (negActionTitle != null) {
            messageDialodBuilder.setNegativeButton(negActionTitle,
                negAction ?: DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
        }
        messageDialodBuilder.setCancelable(canable)
        alertDialog=messageDialodBuilder.show()
    }


}