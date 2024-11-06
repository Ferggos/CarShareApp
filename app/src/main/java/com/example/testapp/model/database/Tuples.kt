package com.example.testapp.model.database

import androidx.room.ColumnInfo

data class AccountInfoTuple(
    val id: Long,
    val mail: String,
    @ColumnInfo(name="registration_date") val registrationDate: String,
    val avatar: String,
    val surname: String,
    val name: String,
    val gender: String,
)
