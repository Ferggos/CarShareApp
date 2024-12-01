package com.example.testapp.domain

import com.example.testapp.data.tables.AccountDataDto

interface AccountRepository {
    suspend fun createUser(account: AccountData): Boolean
    suspend fun getUser(id: String): AccountDataDto
    suspend fun deleteProduct(id: String)
}