package com.example.chat.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.R
import com.example.chat.databinding.ItemRoomBinding
import com.example.chat.model.Room
import com.google.android.gms.common.util.DataUtils

class RoomAdapter(var items: List<Room>) : RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRoomBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_room,
            parent,
            false
        )
        return ViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        holder.binding.root.setOnClickListener {
            onRoomClick?.onClick(item,position)
        }
    }

    fun changData(newsRoom: List<Room>) {
        items = newsRoom
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(var binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(room: Room) {
            binding.room = room

        }
    }

    var onRoomClick: OnRoomClick? = null

    interface OnRoomClick {
        fun onClick(room: Room, position: Int)
    }


}