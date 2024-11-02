package com.example.testapp.screens

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testapp.Utils.FieldValidators.isValidEmail
import com.example.testapp.Utils.FieldValidators.isStringContainNumber
import com.example.testapp.Utils.FieldValidators.isStringContainSpecialCharacter
import com.example.testapp.Utils.FieldValidators.isStringLowerAndUpperCase
import com.example.testapp.databinding.ActivityLoginBinding
import com.example.testapp.screens.Registration.RegistrationActivity
import com.example.testapp.R

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null

    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding for ActivityMainBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLog.setOnClickListener{
            if (isValidate()) {
                Toast.makeText(this, "Чел харош", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        setupListeners()
    }

    private fun isValidate(): Boolean =
        validateEmail() && validatePassword()

    private fun setupListeners() {
        binding.inputMail.addTextChangedListener(TextFieldValidation(binding.inputMail))
        binding.inputPass.addTextChangedListener(TextFieldValidation(binding.inputPass))
    }

    private fun validateEmail(): Boolean {
        if (binding.inputMail.text.toString().trim().isEmpty()) {
            binding.tilMail.error = "Required Field!"
            binding.inputMail.requestFocus()
            return false
        } else if (!isValidEmail(binding.inputMail.text.toString())) {
            binding.tilMail.error = "Invalid Email!"
            binding.inputMail.requestFocus()
            return false
        } else {
            binding.tilMail.isErrorEnabled = false
        }
        return true
    }

    private fun validatePassword(): Boolean {
        if (binding.inputPass.text.toString().trim().isEmpty()) {
            binding.tilPass.error = "Required Field!"
            binding.inputPass.requestFocus()
            return false
        } else if (binding.inputPass.text.toString().length < 6) {
            binding.tilPass.error = "password can't be less than 6"
            binding.inputPass.requestFocus()
            return false
        } else if (!isStringContainNumber(binding.inputPass.text.toString())) {
            binding.tilPass.error = "Required at least 1 digit"
            binding.inputPass.requestFocus()
            return false
        } else if (!isStringLowerAndUpperCase(binding.inputPass.text.toString())) {
            binding.tilPass.error =
                "Password must contain upper and lower case letters"
            binding.inputPass.requestFocus()
            return false
        } else if (!isStringContainSpecialCharacter(binding.inputPass.text.toString())) {
            binding.tilPass.error = "1 special character required"
            binding.inputPass.requestFocus()
            return false
        } else {
            binding.tilPass.isErrorEnabled = false
        }
        return true
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when (view.id) {
                R.id.inputMail -> {
                    validateEmail()
                }

                R.id.inputPass -> {
                    validatePassword()
                }

            }
        }

    }
}