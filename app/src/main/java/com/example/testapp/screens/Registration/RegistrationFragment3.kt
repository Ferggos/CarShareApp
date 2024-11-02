package com.example.testapp.screens.Registration

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.R
import com.example.testapp.databinding.FragmentRegistration3Binding
import com.luccasmelo.kotlinutils.MaskWatcher

class RegistrationFragment3 : Fragment() {

    private var _binding: FragmentRegistration3Binding? = null
    private var selectedImageUri: Uri? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityNoconnectionBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegistration3Binding.inflate(inflater, container, false)

        setupFilters()
        binding.inputDLDate.addTextChangedListener(MaskWatcher("##/##/####"))

        binding.inputDLDate.setOnClickListener {
            showDatePicker(binding.inputDLDate)
        }

        val vp = activity?.findViewById<ViewPager2>(R.id.vp2)

        binding.btnNext.setOnClickListener {
            if (isValidate()) {
                vp?.currentItem = 3
            }
            else Toast.makeText(requireActivity(), "Выполнены не все условия", Toast.LENGTH_SHORT).show()
        }

        binding.ibPhotoExport.setOnClickListener{
            openGallery()
        }

        binding.llDLUploadPhoto.setOnClickListener{
            openGallery()
        }

        binding.llPassportUploadPhoto.setOnClickListener{
            openGallery()
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
        fun newInstance() = RegistrationFragment3()
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryResultLauncher.launch(intent)
    }

    // Обработчик результата выбора изображения из галереи
    private val galleryResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            selectedImageUri = data?.data

            // Загрузите изображение в ImageButton
            //selectedImageUri?.let { uri ->
            //    imageButton.setImageURI(uri)
            //}
        }
    }


    private fun isValidate(): Boolean =
        validateDL() && validateDLDate()

    private fun setupListeners() {
        binding.inputDL.addTextChangedListener(TextFieldValidation(binding.inputDL))
        binding.inputDLDate.addTextChangedListener(TextFieldValidation(binding.inputDLDate))
    }

    private fun validateDL(): Boolean {
        if (binding.inputDL.text.toString().trim().isEmpty()) {
            binding.tilDriverLicense.error = "Required Field!"
            binding.inputDL.requestFocus()
            return false
        } else if (binding.inputDL.text.toString().length < 6) {
            binding.tilDriverLicense.error = "Driver License must be 10 numbers"
            binding.inputDL.requestFocus()
            return false
        } else {
            binding.tilDriverLicense.isErrorEnabled = false
        }
        return true
    }

    private fun validateDLDate(): Boolean {
        if (binding.inputDLDate.text.toString().trim().isEmpty()) {
            binding.tilDriverLicenseDateOfIssue.error = "Required Field!"
            binding.inputDLDate.requestFocus()
            return false
        } else {
            binding.tilDriverLicenseDateOfIssue.isErrorEnabled = false
        }
        return true
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when (view.id) {
                R.id.inputDL -> {
                    validateDL()
                }

                R.id.inputDLDate -> {
                    validateDLDate()
                }
            }

        }
    }
    private fun setupFilters() {
        binding.inputDL.filters = arrayOf(InputFilter.LengthFilter(10), InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!Character.isDigit(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        })

        binding.inputDLDate.filters = arrayOf(InputFilter.LengthFilter(10))
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

        datePickerDialog.show()
    }

}