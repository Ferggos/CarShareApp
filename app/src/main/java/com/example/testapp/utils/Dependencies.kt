package com.example.testapp.utils

import android.content.Context
import androidx.room.Room
import com.example.testapp.model.AccountRepository
import com.example.testapp.model.database.AppDatabase

object Dependencies {

    private var applicationContext: Context? = null

    fun init(context: Context)
    {
        applicationContext = context
    }

    private val appDatabase: AppDatabase by lazy{
        val context = applicationContext ?: throw IllegalStateException("Dependencies.init(context) must be called before accessing appDatabase.")
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
            .build()
    }

    val accountRepository: AccountRepository by lazy {AccountRepository(appDatabase.getAccountDao())}

}