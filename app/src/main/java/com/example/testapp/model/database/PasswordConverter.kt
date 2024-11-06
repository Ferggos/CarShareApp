package com.example.testapp.model.database

import androidx.room.TypeConverter
import org.mindrot.jbcrypt.BCrypt

class PasswordConverter {

    // Хеширование пароля
    @TypeConverter
    fun toHashPass(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    // Проверка пароля
    fun checkPassword(password: String, hashed: String): Boolean {
        return BCrypt.checkpw(password, hashed)
    }
}