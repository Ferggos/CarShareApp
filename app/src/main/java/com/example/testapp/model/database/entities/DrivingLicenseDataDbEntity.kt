package com.example.testapp.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driving_license_data")
data class DrivingLicenseDataDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "dl_number") val dlNum: Long,
    @ColumnInfo(name = "dl_date") val dlDate: String,
    @ColumnInfo(name = "dl_photo") val dlPhoto: String
)
