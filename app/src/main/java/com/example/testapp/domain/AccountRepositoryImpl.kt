package com.example.testapp.domain

import com.example.testapp.data.tables.AccountDataDto
import com.example.testapp.model.database.entities.AccountData
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/*class AccountRepositoryImpl(
    private val postgrest: Postgrest,
    private val storage: Storage
) : AccountRepository {
    override suspend fun createProduct(user: AccountData): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                val accountDto = AccountDataDto(
                    name = user,
                    price = user,
                )
                postgrest.from("products").insert(accountDto)
                true
            }
            true
        } catch (e: java.lang.Exception) {
            throw e
        }
    }
}*/

