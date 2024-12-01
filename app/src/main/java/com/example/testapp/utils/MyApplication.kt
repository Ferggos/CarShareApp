package com.example.testapp.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Dependencies.init(this)
        //val supabaseClient = Dependencies.providesSupabaseClient()
    }
}