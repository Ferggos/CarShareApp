package com.example.testapp.utils

import android.content.Context
import androidx.room.Room
import com.example.testapp.model.AccountRepository
import com.example.testapp.model.database.AppDatabase
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

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

    /*fun providesSupabaseClient(): SupabaseClient
    {
        return createSupabaseClient(
            supabaseUrl = "https://zvfxubkveexenlhzuqdg.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inp2Znh1Ymt2ZWV4ZW5saHp1cWRnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE5OTk3NzgsImV4cCI6MjA0NzU3NTc3OH0.DZjUdqzhSgpkyyvJdhu-sufVyhWhCgO3KyCqXR-QB8A"
        ){
            install(Postgrest)
            install(Storage)
        }
    }*/

}