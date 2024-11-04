package com.example.testapp.screens.Registration

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.model.AccountRepository
import com.example.testapp.model.database.entities.AccountData
import kotlinx.coroutines.launch

class RegistrationViewModel(private val repository: AccountRepository) : ViewModel() {

    private lateinit var mail: String
    private var password: Long = 0
    private lateinit var surname: String
    private lateinit var name: String
    private lateinit var middleName: String
    private lateinit var birthDate: String
    private lateinit var gender: String
    private lateinit var dlNumber: String
    private lateinit var dlDate: String
    private var avatarUri: Uri? = null
    private var passportUri: Uri? = null
    private var dlUri: Uri? = null


    fun registration1ToViewModel(_mail: String, _password: Long){
        mail = _mail
        password = _password
    }

    fun registration2ToViewModel(_surname: String, _name: String, _middleName: String, _birthDate: String, _gender: String){
        surname = _surname
        name = _name
        middleName = _middleName
        birthDate = _birthDate
        gender = _gender
    }

    fun registration3ToViewModel(_dlNumber: String, _dlDate: String, _avatarUri: Uri?, _passportUri: Uri?, _dlUri: Uri?) {
        dlNumber = _dlNumber
        dlDate = _dlDate
        avatarUri = _avatarUri
        passportUri = _passportUri
        dlUri = _passportUri
    }

    fun insertNewAccountDataInDatabase(){
        viewModelScope.launch{
            //val newAccount = AccountData()
        }
    }



}