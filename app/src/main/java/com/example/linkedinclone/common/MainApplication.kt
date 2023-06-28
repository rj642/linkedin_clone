package com.example.linkedinclone.common

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {

    var context: Context? = null

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

}