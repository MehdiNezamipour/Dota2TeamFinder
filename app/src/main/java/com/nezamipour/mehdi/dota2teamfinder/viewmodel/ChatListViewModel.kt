package com.nezamipour.mehdi.dota2teamfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.nezamipour.mehdi.dota2teamfinder.model.ChatRoom

class ChatListViewModel() : ViewModel() {

    val chatRoomList : MutableLiveData<List<ChatRoom>> = MutableLiveData()

}