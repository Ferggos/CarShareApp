package com.example.testapp.model.database

import android.accounts.Account
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.testapp.model.database.entities.AccountData
import com.example.testapp.model.database.entities.AccountDataDbEntity
import com.example.testapp.model.database.entities.DrivingLicenseDataDbEntity
import com.example.testapp.model.database.entities.UserDataDbEntity

@Dao
interface AccountDataDao {

    @Insert
    suspend fun insertUserData(userData: UserDataDbEntity): Long

    @Insert
    suspend fun insertDrivingLicenseData(dlData: DrivingLicenseDataDbEntity): Long

    @Insert
    suspend fun insertAccountData(accountData: AccountDataDbEntity)

    @Transaction
    suspend fun insertNewAccountData(accountData: AccountData) {
        val userId = insertUserData(accountData.toUserDataDbEntity())

        val dlId = insertDrivingLicenseData(accountData.toDrivingLicenseDataDbEntity())

        insertAccountData(accountData.toAccountDataDbEntity(userId, dlId))
    }

    @Query("SELECT account_data.id, mail, avatar, registration_date, name, surname, gender FROM account_data\n" +
            "INNER JOIN user_data ON account_data.user_data_id = user_data.id\n" +
            "INNER JOIN driving_license_data ON account_data.dl_data_id = driving_license_data.id;")
    fun getAccountData(): List<AccountInfoTuple>

    @Query("DELETE FROM account_data WHERE id = :accountDataId")
    fun deleteAccountDataById(accountDataId: Long)
}