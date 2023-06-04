package com.example.chat.ui.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityChatBinding
import com.example.chat.model.Message
import com.example.chat.model.MyUser
import com.example.chat.model.Room
import com.example.chat.ui.base.BaseActivity
import com.example.chat.ui.home.HomeViewModel
import java.util.*

class ChatActivity : BaseActivity<ChatViewModel,ActivityChatBinding>(), chatNavegator {
    var adapter=ChatAdapter(mutableListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.room=intent.getSerializableExtra("room")as Room
        viewModel.navegator=this
        initLisener()
        observView()
        initViews()
       viewModel.subscribeToMessageCollection()





    }

    override fun getLayoutId(): Int = R.layout.activity_chat

    override fun initViewModel(): ChatViewModel {
        return ViewModelProvider(this).get(ChatViewModel::class.java)
    }
    fun initLisener(){

        viewBinding.btnSend.setOnClickListener {
            var message= Message(
                id="",
                content=viewBinding.messageEd.text.toString(),
                time = Calendar.getInstance().time.time,
                senderName = MyUser.currentUser!!.firstName+" "+MyUser.currentUser!!.lastName,
                senderId = MyUser.currentUser!!.id,
            )
            viewModel.sendMessage(message)


        }

    }
    fun initViews(){
        viewBinding.recycleView.adapter=adapter

    }

    override fun onSendMessage() {
        viewBinding.messageEd.text=null
    }
    fun observView(){
        viewModel.messageLiveData.observe(this){
          adapter.changData(it)
        }
    }
}