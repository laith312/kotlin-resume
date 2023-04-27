package com.alnagem.resume

import android.app.Application
import android.content.Context

class ResumeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }
}