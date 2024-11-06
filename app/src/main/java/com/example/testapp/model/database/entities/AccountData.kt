package com.example.testapp.model.database.entities

import androidx.room.TypeConverters
import com.example.testapp.model.database.PasswordConverter

data class AccountData(
    val mail: String,
    @TypeConverters(PasswordConverter::class) val hashPassword: String,
    val registrationDate: String,
    val avatar: String,
    val userData: UserData,
    val drivingLicenseData: DrivingLicenseData
){

    fun toAccountDataDbEntity(userId: Long, dlId: Long): AccountDataDbEntity {
        return AccountDataDbEntity(
            id = 0,
            mail = mail,
            hashPassword = hashPassword,
            registrationDate = registrationDate,
            avatar = avatar,
            userDataId = userId,
            dlDataId = dlId
        )
    }

    fun toUserDataDbEntity(): UserDataDbEntity {
        return UserDataDbEntity(
            id = 0,
            surname = userData.surname,
            name = userData.name,
            middleName = userData.middleName,
            birthDate = userData.birthDate,
            gender = userData.gender,
            passportPhoto = userData.passportPhoto
        )
    }

    fun toDrivingLicenseDataDbEntity(): DrivingLicenseDataDbEntity {
        return DrivingLicenseDataDbEntity(
            id = 0,
            dlNum = drivingLicenseData.dlNum,
            dlDate = drivingLicenseData.dlDate,
            dlPhoto = drivingLicenseData.dlPhoto
        )
    }
}
