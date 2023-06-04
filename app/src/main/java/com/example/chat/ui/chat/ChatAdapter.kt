package com.example.chat.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.R
import com.example.chat.databinding.ItemRecivedMessageBinding
import com.example.chat.databinding.ItemRoomBinding
import com.example.chat.databinding.ItemSentMessageBinding
import com.example.chat.model.Message
import com.example.chat.model.MyUser
import com.example.chat.model.Room
import com.google.android.gms.common.util.DataUtils

class ChatAdapter(var items: List<Message>) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType==1){
            val binding = DataBindingUtil.inflate<ItemSentMessageBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_sent_message,
                parent,
                false
            )
            return ViewHolder(binding = binding)
        }else {
            val binding = DataBindingUtil.inflate<ItemRecivedMessageBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_recived_message,
                parent,
                false
            )
            return ViewHolder(binding = binding)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val  message=items[position]
        if(message.senderId.equals(MyUser.currentUser?.id)){
            return 1
        }else{
            return 2
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

    }

    fun changData(newsMessages: List<Message>) {
        items =newsMessages
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            if(binding is ItemSentMessageBinding){
                binding.message=message

            }else if(binding is ItemRecivedMessageBinding){
              binding.message=message

            }


        }
    }



}