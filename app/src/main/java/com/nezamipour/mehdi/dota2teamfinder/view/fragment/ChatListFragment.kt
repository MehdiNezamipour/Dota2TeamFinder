package com.nezamipour.mehdi.dota2teamfinder.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nezamipour.mehdi.dota2teamfinder.R
import com.nezamipour.mehdi.dota2teamfinder.adapter.ChatRoomListAdapter
import com.nezamipour.mehdi.dota2teamfinder.databinding.FragmentChatListBinding
import com.nezamipour.mehdi.dota2teamfinder.viewmodel.ChatListViewModel

class ChatListFragment : Fragment() {

    private lateinit var binding: FragmentChatListBinding
    private val root = FirebaseDatabase.getInstance().reference.root
    private val map = hashMapOf<String, String>()
    private lateinit var adapter: ChatRoomListAdapter
    private lateinit var viewModel: ChatListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatListViewModel::class.java)
        viewModel.chatRoomList.observe(this, Observer {
            adapter.
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonAddChatRoom.setOnClickListener {
            map.put(binding.editTextChatRoomName.text.toString(), "")
            root.updateChildren(map as Map<String, Any>)

        }

        root.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
                viewModel.chatRoomList = snapshot.value

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }

}