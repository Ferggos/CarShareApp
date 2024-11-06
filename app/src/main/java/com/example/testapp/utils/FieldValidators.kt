package com.example.testapp.utils


object FieldValidators {
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isStringContainNumber(string: String): Boolean {
        return string.any { it.isDigit() }
    }

    fun isStringContainSpecialCharacter(string: String): Boolean {
        return string.any { !it.isLetterOrDigit() }
    }

    fun isStringLowerAndUpperCase(string: String): Boolean {
        return string.any { it.isLowerCase() } && string.any { it.isUpperCase() }
    }
}
