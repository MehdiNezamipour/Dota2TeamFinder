package com.nezamipour.mehdi.dota2teamfinder

import android.app.Application
import com.nezamipour.mehdi.dota2teamfinder.di.chatListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(chatListViewModel))
        }
    }
}