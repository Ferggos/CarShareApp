package com.example.testapp.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "account_data",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = UserDataDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_data_id"]
        ),
        ForeignKey(
            entity = DrivingLicenseDataDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["dl_data_id"]
        )
    ]
)
data class AccountDataDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val mail: String,
    @ColumnInfo(name="password")val hashPassword: String,
    @ColumnInfo(name="registration_date") val registrationDate: LocalDate,
    val avatar: ByteArray,
    @ColumnInfo(name = "user_data_id") val userDataId: Long,
    @ColumnInfo(name = "dl_data_id") val dlDataId: Long
)
