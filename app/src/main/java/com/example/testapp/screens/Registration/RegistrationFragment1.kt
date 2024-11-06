package com.example.testapp.screens.Registration

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.R
import com.example.testapp.utils.Dependencies
import com.example.testapp.databinding.FragmentRegistration1Binding
import com.example.testapp.utils.FieldValidators.isValidEmail
import com.example.testapp.utils.FieldValidators.isStringContainNumber
import com.example.testapp.utils.FieldValidators.isStringContainSpecialCharacter
import com.example.testapp.utils.FieldValidators.isStringLowerAndUpperCase


class RegistrationFragment1 : Fragment() {

    private var _binding: FragmentRegistration1Binding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityNoconnectionBinding must not be null")

    //private val viewModel by lazy { RegistrationViewModel(Dependencies.accountRepository) }

    private val viewModel by activityViewModels<RegistrationViewModel> { RegistrationViewModelFactory(Dependencies.accountRepository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistration1Binding.inflate(inflater, container, false)

        val vp = activity?.findViewById<ViewPager2>(R.id.vp2)
        //val viewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
        binding.btnNext.setOnClickListener {
            if (isValidate()) {
                vp?.currentItem = 1
                val mail = binding.inputMail.text.toString()
                val password = binding.inputPass.text.toString()
                viewModel.registration1ToViewModel(mail, password)
            }
            else Toast.makeText(requireActivity(), "Выполнены не все условия", Toast.LENGTH_SHORT).show()
        }
        setupListeners()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment1()
    }

    private fun isValidate(): Boolean =
        validateEmail() && validatePassword() && validateConfirmPassword() && validateCheckBox()

    private fun setupListeners() {
        binding.inputMail.addTextChangedListener(TextFieldValidation(binding.inputMail))
        binding.inputPass.addTextChangedListener(TextFieldValidation(binding.inputPass))
        binding.inputPassRepeat.addTextChangedListener(TextFieldValidation(binding.inputPassRepeat))
        binding.cbAgreement.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.tvCheckBoxError.visibility = View.GONE
            }
        }
    }

    private fun validateCheckBox(): Boolean {
        return if (!binding.cbAgreement.isChecked) {
            binding.tvCheckBoxError.visibility = View.VISIBLE
            false
        } else {
            binding.tvCheckBoxError.visibility = View.GONE
            true
        }
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

    private fun validateConfirmPassword(): Boolean {
        when {
            binding.inputPassRepeat.text.toString().trim().isEmpty() -> {
                binding.tilPassRepeat.error = "Required Field!"
                binding.inputPassRepeat.requestFocus()
                return false
            }
            binding.inputPassRepeat.text.toString() != binding.inputPass.text.toString() -> {
                binding.tilPassRepeat.error = "Passwords don't match"
                binding.inputPassRepeat.requestFocus()
                return false
            }
            else -> {
                binding.tilPassRepeat.isErrorEnabled = false
            }
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
                R.id.inputPassRepeat -> {
                    validateConfirmPassword()
                }

            }
        }

    }

}