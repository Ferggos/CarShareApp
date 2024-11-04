package com.example.testapp.model.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "available_cars")
data class AvailableCarsDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val brand: String,
    val model: String,
    @ColumnInfo(name = "transmission_type") val transmissionType: String,
    @ColumnInfo(name = "engine_type") val engineType: String,
    val price: String,
)
