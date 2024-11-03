package com.example.testapp.Utils

import android.content.Context
import androidx.room.Room
import com.example.testapp.model.AccountRepository
import com.example.testapp.model.database.AppDatabase

object Dependencies {

    private lateinit var applicationContext: Context

    fun init(context: Context)
    {
        applicationContext = context
    }

    private val appDatabase: AppDatabase by lazy{
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db")
            .createFromAsset("room_database.db")
            .build()
    }

    val accountRepository: AccountRepository by lazy {AccountRepository(appDatabase.getAccountDao())}

}