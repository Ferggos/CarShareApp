package com.example.testapp.model

import com.example.testapp.model.database.AccountDataDao
import com.example.testapp.model.database.AccountInfoTuple
import com.example.testapp.model.database.entities.AccountData
import com.example.testapp.model.database.entities.AccountDataDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepository(private val accountDao: AccountDataDao) {

    suspend fun insertNewAccountData(accountData: AccountData){
        withContext(Dispatchers.IO){
            accountDao.insertNewAccountData(accountData)
        }
    }

    suspend fun getAccountData(): List<AccountInfoTuple>{
        return withContext(Dispatchers.IO){
            return@withContext accountDao.getAccountData()
        }
    }

    suspend fun removeAccountDataById(id: Long){
        withContext(Dispatchers.IO){
            accountDao.deleteAccountDataById(id)
        }
    }

}