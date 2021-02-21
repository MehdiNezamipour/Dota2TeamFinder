package com.nezamipour.mehdi.dota2teamfinder.view.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nezamipour.mehdi.dota2teamfinder.databinding.FragmentLoginDialogBinding
import com.nezamipour.mehdi.dota2teamfinder.model.User
import com.nezamipour.mehdi.dota2teamfinder.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class LoginDialogFragment : DialogFragment() {

    private val viewModel: ChatViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val binding = FragmentLoginDialogBinding.inflate(layoutInflater)

        return MaterialAlertDialogBuilder(this.requireContext())
            .setTitle("Login")
            .setView(binding.root)
            .setPositiveButton(
                android.R.string.ok
            ) { dialog, which ->
                viewModel.user = User(userName = binding.editTextUserName.text.toString())
            }
            .setNegativeButton(
                android.R.string.cancel
            ) { dialog, which ->

            }
            .show()
    }
}