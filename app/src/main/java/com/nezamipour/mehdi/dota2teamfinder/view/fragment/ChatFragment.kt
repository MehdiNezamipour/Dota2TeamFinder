package com.nezamipour.mehdi.dota2teamfinder.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.*
import com.nezamipour.mehdi.dota2teamfinder.databinding.FragmentChatBinding
import com.nezamipour.mehdi.dota2teamfinder.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChatFragment : Fragment() {

    private lateinit var root: DatabaseReference
    private lateinit var binding: FragmentChatBinding
    private val args: ChatFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val chatRoomName = args.chatRoomName
        root = FirebaseDatabase.getInstance().reference.child(chatRoomName)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val userName = sharedPref?.getString("userName", "")

        binding.buttonSend.setOnClickListener {
            // add children to no sql (type = key , value) fireBase database
            val map = hashMapOf<String, Any>()
            val tempKey = root.push().key
            root.updateChildren(map)

            val messageRoot = tempKey?.let { it1 -> root.child(it1) }
            val map2 = hashMapOf<String, Any>()
            map2["name"] = userName.toString()
            map2["message"] = binding.editTextTextPersonName.text.toString()

            messageRoot?.updateChildren(map2)
        }

        root.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                appendChatConversation(snapshot)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                appendChatConversation(snapshot)
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun appendChatConversation(snapshot: DataSnapshot) {
        val iterator = snapshot.children.iterator()
        while (iterator.hasNext()) {
            val message = iterator.next().value.toString()
            val userName = iterator.next().value.toString()
            binding.textViewConversation.append("$userName : $message\n")
        }
    }


}