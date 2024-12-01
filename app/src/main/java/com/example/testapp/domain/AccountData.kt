package com.example.testapp.domain


data class AccountData(
    val mail: String,
    val hashPassword: String,
    val registrationDate: String,
    val avatar: String,
    val userData: UserData,
    val drivingLicenseData: DrivingLicenseData
)
