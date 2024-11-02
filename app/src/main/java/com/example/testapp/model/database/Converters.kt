package com.example.testapp.model.database

import androidx.room.TypeConverter
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Converters {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    // Конвертация даты
    @TypeConverter
    fun fromString(value: String?): LocalDate? {
        return value?.let { LocalDate.parse(it, formatter) }
    }

    @TypeConverter
    fun dateToString(date: LocalDate?): String? {
        return date?.format(formatter)
    }

    // Конвертация для изображений
    @TypeConverter
    fun fromByteArray(byteArray: ByteArray?): String? {
        return byteArray?.let { String(it) }
    }

    @TypeConverter
    fun toByteArray(imageString: String?): ByteArray? {
        return imageString?.toByteArray()
    }

    // Хеширование пароля
    @TypeConverter
    fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    // Проверка пароля
    fun checkPassword(password: String, hashed: String): Boolean {
        return BCrypt.checkpw(password, hashed)
    }
}
