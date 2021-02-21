package com.nezamipour.mehdi.dota2teamfinder.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nezamipour.mehdi.dota2teamfinder.adapter.ChatRoomListAdapter
import com.nezamipour.mehdi.dota2teamfinder.databinding.FragmentChatListBinding
import com.nezamipour.mehdi.dota2teamfinder.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChatListFragment : Fragment() {

    private lateinit var binding: FragmentChatListBinding
    private val root = FirebaseDatabase.getInstance().reference.root
    private val map = hashMapOf<String, String>()
    private lateinit var adapter: ChatRoomListAdapter
    private val viewModel: ChatViewModel by sharedViewModel()

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

        Navigation.findNavController(view)
            .navigate(ChatListFragmentDirections.actionChatListFragmentToLoginDialogFragment())

        binding.buttonAddChatRoom.setOnClickListener {
            map.put(binding.editTextChatRoomName.text.toString(), "")
            root.updateChildren(map as Map<String, Any>)

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