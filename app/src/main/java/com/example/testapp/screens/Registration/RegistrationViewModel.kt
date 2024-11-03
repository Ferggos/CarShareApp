package com.example.testapp.screens.Registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.AccountRepository
import com.example.testapp.model.database.entities.AccountData
import kotlinx.coroutines.launch

class RegistrationViewModel(private val repository: AccountRepository) : ViewModel() {

    fun insertNewAccountDataInDatabase(){
        viewModelScope.launch{
        }
    }



}