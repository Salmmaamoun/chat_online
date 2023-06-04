package com.example.chat.ui.add_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.example.chat.R
import com.example.chat.databinding.ActivityAddRoomBinding
import com.example.chat.model.Room
import com.example.chat.ui.base.BaseActivity
import com.example.chat.ui.home.HomeViewModel

class AddRoomActivity : BaseActivity<AddRoomViewModel, ActivityAddRoomBinding>(),AddRoomNav {
    var selectedCategory=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm=viewModel
        viewModel.navigator=this
        initLisener()

    }

    override fun getLayoutId(): Int = R.layout.activity_add_room
    override fun initViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }
    fun initLisener(){
        viewBinding.btnCreatRoom.setOnClickListener{
            val room=Room(
                tittle=viewBinding.roomTittleLayout.editText?.text.toString(),
                describtion =viewBinding.describtionLayout.editText?.text.toString(),
                roomCategory = if(selectedCategory.isEmpty())"Music" else selectedCategory

            )
            viewModel.creatRoom(room)


        }
        viewBinding.spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val array=resources.getStringArray(R.array.room_category)
                array[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                TODO("Not yet implemented")
            }

        }


    }

    override fun navigatorToHome() {
        finish()
    }
}