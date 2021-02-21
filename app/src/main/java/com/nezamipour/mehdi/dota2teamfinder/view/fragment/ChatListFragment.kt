package com.nezamipour.mehdi.dota2teamfinder.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nezamipour.mehdi.dota2teamfinder.adapter.ChatRoomListAdapter
import com.nezamipour.mehdi.dota2teamfinder.databinding.FragmentChatListBinding
import com.nezamipour.mehdi.dota2teamfinder.viewmodel.ChatViewModel

class ChatListFragment : Fragment() {

    private lateinit var binding: FragmentChatListBinding
    private val root = FirebaseDatabase.getInstance().reference.root
    private val map = hashMapOf<String, String>()
    private lateinit var adapter: ChatRoomListAdapter
    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.chatRoomList.observe(this, Observer {
            adapter.chatRoomList = it
            adapter.notifyDataSetChanged()
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
        adapter = ChatRoomListAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val userName = sharedPref?.getString("userName", "")
        if (userName.isNullOrEmpty())
            Navigation.findNavController(view)
                .navigate(ChatListFragmentDirections.actionChatListFragmentToLoginDialogFragment())

        binding.buttonAddChatRoom.setOnClickListener {
            if (!binding.editTextChatRoomName.text.isNullOrEmpty()) {
                map[binding.editTextChatRoomName.text.toString()] = ""
                root.updateChildren(map as Map<String, Any>)
            } else
                Toast.makeText(
                    activity?.applicationContext,
                    "Fill ChatRoom Name",
                    Toast.LENGTH_SHORT
                ).show()

        }

        root.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val set = hashSetOf<String>()
                val list = mutableListOf<String>()
                val iterator = snapshot.children.iterator()

                while (iterator.hasNext()) {
                    iterator.next().key?.let { set.add(it) }
                }
                list.addAll(set)
                viewModel.chatRoomList.value = list

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

}