package com.example.testapp.model.database.entities

import java.time.LocalDate

data class UserData(
    val surname: String,
    val name: String,
    val middleName: String,
    val birthDate: LocalDate,
    val gender: String,
    val passportPhoto: ByteArray
)

