package com.example.testapp.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "user_data")
data class UserDataDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val surname: String,
    val name: String,
    @ColumnInfo(name = "middle_name") val middleName: String,
    @ColumnInfo(name = "birth_date") val birthDate: LocalDate,
    val gender: String,
    @ColumnInfo(name = "passport_photo") val passportPhoto: ByteArray
)
