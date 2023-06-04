package com.example.chat.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityHomeBinding
import com.example.chat.databinding.ActivityLoginBinding
import com.example.chat.model.Room
import com.example.chat.ui.add_room.AddRoomActivity
import com.example.chat.ui.base.BaseActivity
import com.example.chat.ui.chat.ChatActivity
import com.example.chat.ui.login.LoginViewModel
import com.example.chat.ui.register.RegActivity

class HomeActivity : BaseActivity<HomeViewModel, ActivityHomeBinding>() {
    var adapter:RoomAdapter =  RoomAdapter(listOf<Room>())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        initViews()
        observeViewModewl()
        initLisener()



    }

    override fun onStart() {
        super.onStart()
        viewModel.getRooms()
    }

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    fun initViews() {
        viewBinding.recycleView.adapter = adapter

    }

    fun initLisener() {
        viewBinding.addBtn.setOnClickListener {
            var intent = Intent(this@HomeActivity, AddRoomActivity::class.java)
            startActivity(intent)
        }
        adapter.onRoomClick = object : RoomAdapter.OnRoomClick {
            override fun onClick(room: Room, position: Int) {
                var intent =Intent(this@HomeActivity,ChatActivity::class.java)
                intent.putExtra("room",room)
                startActivity(intent)

            }

        }
    }

    fun observeViewModewl() {
        viewModel.roomLiveData.observe(this) {
            adapter.changData(it.toList())
        }
    }
}