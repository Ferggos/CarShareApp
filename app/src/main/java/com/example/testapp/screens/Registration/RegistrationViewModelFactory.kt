package com.example.testapp.screens.Registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.model.AccountRepository

class RegistrationViewModelFactory(private val accountRepository: AccountRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegistrationViewModel::class.java)) {
            return RegistrationViewModel(accountRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
