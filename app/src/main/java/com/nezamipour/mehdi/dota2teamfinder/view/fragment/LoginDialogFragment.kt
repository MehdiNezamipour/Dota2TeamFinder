package com.nezamipour.mehdi.dota2teamfinder.view.fragment

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nezamipour.mehdi.dota2teamfinder.databinding.FragmentLoginDialogBinding
import com.nezamipour.mehdi.dota2teamfinder.model.User
import com.nezamipour.mehdi.dota2teamfinder.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class LoginDialogFragment : DialogFragment() {



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
                if (!binding.editTextUserName.text.isNullOrEmpty()) {
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                    if (sharedPref != null) {
                        with(sharedPref.edit()) {
                            putString("userName", binding.editTextUserName.text.toString())
                            apply()
                        }
                    }
                }
            }
            .setNegativeButton(
                android.R.string.cancel
            ) { dialog, which ->

            }
            .show()
    }
}