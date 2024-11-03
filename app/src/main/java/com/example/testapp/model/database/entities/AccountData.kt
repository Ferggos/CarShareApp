package com.example.testapp.model.database.entities

import java.time.LocalDate

data class AccountData(
    val mail: String,
    val hashPassword: String,
    val registrationDate: LocalDate,
    val avatar: ByteArray,
    val userDataId: Long,
    val dlDataId: Long
){

    fun toAccountDataDbEntity():AccountDataDbEntity = AccountDataDbEntity(
        id = 0,
        mail = mail,
        hashPassword = hashPassword,
        registrationDate = registrationDate,
        avatar = avatar,
        userDataId = userDataId,
        dlDataId = dlDataId
    )
}
