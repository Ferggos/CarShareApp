package com.example.testapp.data.tables

import kotlinx.serialization.Serializable

@Serializable
data class DrivingLicenseDataDto(
    val dlNum: Long,
    val dlDate: String,
    val dlPhoto: String
)
