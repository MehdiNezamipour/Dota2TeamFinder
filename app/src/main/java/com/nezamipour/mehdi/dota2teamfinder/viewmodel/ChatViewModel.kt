package com.nezamipour.mehdi.dota2teamfinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nezamipour.mehdi.dota2teamfinder.model.User

class ChatViewModel() : ViewModel() {

    val chatRoomList: MutableLiveData<List<String>> = MutableLiveData()
    lateinit var user: User

}