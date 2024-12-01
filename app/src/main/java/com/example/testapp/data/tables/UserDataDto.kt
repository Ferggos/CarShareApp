package com.example.testapp.data.tables

import kotlinx.serialization.Serializable

@Serializable
data class UserDataDto(
    val surname: String,
    val name: String,
    val middleName: String,
    val birthDate: String,
    val gender: String,
    val passportPhoto: String
)

