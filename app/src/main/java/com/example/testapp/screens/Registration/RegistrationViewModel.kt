package com.example.testapp.screens.Registration

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.AccountRepository
import com.example.testapp.model.database.entities.AccountData
import com.example.testapp.model.database.entities.DrivingLicenseData
import com.example.testapp.model.database.entities.UserData
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class RegistrationViewModel(private val repository: AccountRepository) : ViewModel() {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    private lateinit var mail: String
    private lateinit var password: String
    private lateinit var surname: String
    private lateinit var name: String
    private lateinit var middleName: String
    private lateinit var birthDate: String
    private lateinit var gender: String
    private var dlNumber: Long = 0
    private lateinit var dlDate: String
    private lateinit var avatarUri: String
    private lateinit var passportUri: String
    private lateinit var dlUri: String
    private var registrationDate: LocalDate = LocalDate.now()


    fun registration1ToViewModel(_mail: String, _password: String){
        viewModelScope.launch {
            mail = _mail
            password = _password
        }
    }

    fun registration2ToViewModel(_surname: String, _name: String, _middleName: String, _birthDate: String, _gender: String){
        viewModelScope.launch {
            surname = _surname
            name = _name
            middleName = _middleName
            birthDate = _birthDate
            gender = _gender

        }
    }

    fun registration3ToViewModel(_dlNumber: Long, _dlDate: String, _avatarUri: String, _passportUri: String, _dlUri: String) {
        viewModelScope.launch {
            dlNumber = _dlNumber
            dlDate = _dlDate
            avatarUri = _avatarUri
            passportUri = _passportUri
            dlUri = _dlUri
        }
    }
    fun insertNewAccountDataInDatabase(){
        viewModelScope.launch{
            val newUserData = UserData(surname, name, middleName, birthDate, gender, passportUri)
            val newDLData = DrivingLicenseData(dlNumber, dlDate, dlUri)
            val newAccount = AccountData(mail, password, dateToString(registrationDate), avatarUri, newUserData, newDLData)
            repository.insertNewAccountData(newAccount)
        }
    }

    // Конвертация даты
    private fun dateToString(date: LocalDate): String {
        return date.format(formatter)
    }



}