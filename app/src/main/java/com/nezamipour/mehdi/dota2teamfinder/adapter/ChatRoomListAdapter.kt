package com.nezamipour.mehdi.dota2teamfinder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nezamipour.mehdi.dota2teamfinder.databinding.ChatListItemLayoutBinding
import com.nezamipour.mehdi.dota2teamfinder.model.ChatRoom

class ChatRoomListAdapter(val chatRoomList: List<ChatRoom>) :
    RecyclerView.Adapter<ChatRoomListAdapter.ChatRoomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomViewHolder {
        val binding =
            ChatListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatRoomViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ChatRoomViewHolder, position: Int) {
        val item = chatRoomList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return chatRoomList.size
    }


    // viewHolder class
    class ChatRoomViewHolder(private val binding: ChatListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatRoom: ChatRoom) {
            binding.tvChatRoomFirstLetter.text = chatRoom.name.get(0).toString()
            binding.tvChatRoomName.text = chatRoom.name
        }
    }


    // TODO use later for more speed when update list
    // comparator object
    class ChatRoomListComparator(
        private val oldList: List<ChatRoom>,
        private val newList: List<ChatRoom>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id === newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val chatRoom1 = oldList[oldItemPosition]
            val chatRoom2 = newList[newItemPosition]
            return chatRoom1 == chatRoom2 && chatRoom1.name == chatRoom2.name
        }

    }

}