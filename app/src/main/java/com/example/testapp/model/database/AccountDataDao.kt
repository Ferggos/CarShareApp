package com.example.testapp.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testapp.model.database.entities.AccountDataDbEntity

@Dao
interface AccountDataDao {

    @Insert(entity = AccountDataDbEntity::class)
    fun insertNewAccountData(accountData: AccountDataDbEntity)

    @Query("SELECT account_data.id, avatar, mail, registration_date, name, surname, gender FROM account_data\n" +
            "INNER JOIN user_data ON account_data.user_data_id = user_data.id\n" +
            "INNER JOIN driving_license_data ON account_data.dl_data_id = driving_license_data.id;")
    fun getAccountData(): List<AccountInfoTuple>

    @Query("DELETE FROM account_data WHERE id = :accountDataId")
    fun deleteStatisticDataById(accountDataId: Long)
}