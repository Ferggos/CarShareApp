package com.example.testapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testapp.model.database.entities.AccountDataDbEntity
import com.example.testapp.model.database.entities.AvailableCarsDbEntity
import com.example.testapp.model.database.entities.DrivingLicenseDataDbEntity
import com.example.testapp.model.database.entities.UserDataDbEntity

@Database(
    version = 1,
    entities = [
        UserDataDbEntity::class,
        DrivingLicenseDataDbEntity::class,
        AccountDataDbEntity::class,
        AvailableCarsDbEntity::class
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountDao(): AccountDataDao

}