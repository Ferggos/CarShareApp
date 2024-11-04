package com.example.testapp.model.database.entities


data class UserData(
    val surname: String,
    val name: String,
    val middleName: String,
    val birthDate: String,
    val gender: String,
    val passportPhoto: ByteArray
)

