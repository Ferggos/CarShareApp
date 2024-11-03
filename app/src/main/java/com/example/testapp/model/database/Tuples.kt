package com.example.testapp.model.database

import androidx.room.ColumnInfo
import java.time.LocalDate

data class AccountInfoTuple(
    val id: Long,
    val mail: String,
    @ColumnInfo(name="registration_date") val registrationDate: LocalDate,
    val avatar: ByteArray,
    val surname: String,
    val name: String,
    val gender: String,
)
