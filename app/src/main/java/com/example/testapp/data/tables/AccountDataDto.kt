package com.example.testapp.data.tables

import android.annotation.SuppressLint
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDataDto(
    @SerialName("mail")
    val mail: String,
    @SerialName("password")
    val hashPassword: String,
    @SerialName("registration_date")
    val registrationDate: String,
    @SerialName("avatar")
    val avatar: String,
    val userDataDto: UserDataDto,
    val drivingLicenseDataDto: DrivingLicenseDataDto
)
