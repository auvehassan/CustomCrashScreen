package com.auvehassan.customerrorscreen

import android.app.Application

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalExceptionHandler.initialize(this, CrashActivity::class.java)
    }

}