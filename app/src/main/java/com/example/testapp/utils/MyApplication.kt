package com.example.testapp.utils

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Dependencies.init(this)
    }
}