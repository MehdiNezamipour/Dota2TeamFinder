package com.nezamipour.mehdi.dota2teamfinder.di

import com.nezamipour.mehdi.dota2teamfinder.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val  chatListViewModel = module {
    viewModel { ChatViewModel() }
}