package com.example.testapp.screens.Registration

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.R
import com.example.testapp.databinding.FragmentRegistration2Binding
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.example.testapp.utils.Dependencies
import com.google.android.material.textfield.TextInputLayout
import com.luccasmelo.kotlinutils.MaskWatcher


class RegistrationFragment2 : Fragment() {

    private var _binding: FragmentRegistration2Binding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityNoconnectionBinding must not be null")

    //private val viewModel by lazy { RegistrationViewModel(Dependencies.accountRepository) }

    private val viewModel by activityViewModels<RegistrationViewModel> { RegistrationViewModelFactory(Dependencies.accountRepository) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegistration2Binding.inflate(inflater, container, false)

        val vp = activity?.findViewById<ViewPager2>(R.id.vp2)

        binding.inputBirthDate.filters = arrayOf(InputFilter.LengthFilter(10))
        binding.inputBirthDate.addTextChangedListener(MaskWatcher("##/##/####"))

        // Установка обработчика нажатия на inputBirthDate для отображения календаря
        binding.inputBirthDate.setOnClickListener {
            showDatePicker(binding.inputBirthDate)
        }
        //val viewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]
        binding.btnNext.setOnClickListener {
            if (isValidate()) {
                val surname = binding.inputSurname.text.toString()
                val name = binding.inputName.text.toString()
                val middleName = binding.inputMiddleName.text.toString()
                val birthDate = binding.inputBirthDate.text.toString()
                val selectedGender = when (binding.rgGender.checkedRadioButtonId) {
                    binding.radioMale.id -> "Мужской"
                    binding.radioFemale.id -> "Женский"
                    else -> "Not Selected"
                }
                viewModel.registration2ToViewModel(surname, name, middleName, birthDate, selectedGender)
                vp?.currentItem = 2
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
        fun newInstance() = RegistrationFragment2()
    }

    private fun isValidate(): Boolean =
        validateField(binding.inputSurname, binding.tilSurname) && validateField(binding.inputName, binding.tilName) &&
                validateField(binding.inputMiddleName, binding.tilMiddleName) && validateField(binding.inputBirthDate, binding.tilBirthDate)

    private fun setupListeners() {
        binding.inputSurname.addTextChangedListener(TextFieldValidation(binding.inputSurname))
        binding.inputName.addTextChangedListener(TextFieldValidation(binding.inputName))
        binding.inputMiddleName.addTextChangedListener(TextFieldValidation(binding.inputMiddleName))
        binding.inputBirthDate.addTextChangedListener(TextFieldValidation(binding.inputBirthDate))
    }

    private fun validateField(inputField: EditText, inputLayout: TextInputLayout): Boolean {
        if (inputField.text.toString().trim().isEmpty()) {
            inputLayout.error = "Required Field!"
            inputField.requestFocus()
            return false
        } else {
            inputLayout.isErrorEnabled = false
        }
        return true
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when (view.id) {
                R.id.inputSurname -> {
                    validateField(binding.inputSurname, binding.tilSurname)
                }
                R.id.inputName -> {
                    validateField(binding.inputName, binding.tilName)
                }
                R.id.inputMiddleName -> {
                    validateField(binding.inputMiddleName, binding.tilMiddleName)
                }
                R.id.inputBirthDate -> {
                    validateField(binding.inputBirthDate, binding.tilBirthDate)
                }
            }
        }

    }

    private fun showDatePicker(targetEditText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)

                // Calculate the date for 18 years ago
                val ageThreshold = Calendar.getInstance()
                ageThreshold.add(Calendar.YEAR, -18)

                if (selectedDate.before(ageThreshold)) {
                    // Format and set the date if valid
                    val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                    targetEditText.setText(formattedDate)
                } else {
                    // Show error if under 18
                    Toast.makeText(requireContext(), "Age must be 18 or older", Toast.LENGTH_SHORT).show()
                }
            },
            year, month, day
        )

        // Show the date picker dialog
        datePickerDialog.show()
    }



}