package com.example.testapp.model.database.entities

import java.time.LocalDate

data class DrivingLicenseData(
    val dlNum: Long,
    val dlDate: LocalDate,
    val dlPhoto: ByteArray
)
