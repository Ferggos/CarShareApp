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
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.R
import com.example.testapp.utils.Dependencies
import com.example.testapp.databinding.FragmentRegistration3Binding
import com.luccasmelo.kotlinutils.MaskWatcher

class RegistrationFragment3 : Fragment() {

    private var _binding: FragmentRegistration3Binding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityNoconnectionBinding must not be null")

    //private val viewModel by lazy { RegistrationViewModel(Dependencies.accountRepository) }

    private val viewModel by activityViewModels<RegistrationViewModel> { RegistrationViewModelFactory(Dependencies.accountRepository) }

    private var avatarPhotoUri: Uri? = null
    private var dlUploadPhotoUri: Uri? = null
    private var passportUploadPhotoUri: Uri? = null
    private var currentPhotoType: PhotoType? = null

    private enum class PhotoType {
        AVATAR, DL_UPLOAD, PASSPORT
    }

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

        //val viewModel = ViewModelProvider(requireActivity())[RegistrationViewModel::class.java]

        binding.btnNext.setOnClickListener {
            if (isValidate()) {
                val dlNumber = binding.inputDL.text.toString().toLong()
                val dlDate = binding.inputDLDate.text.toString()
                viewModel.registration3ToViewModel(dlNumber, dlDate, avatarPhotoUri.toString(), passportUploadPhotoUri.toString(), dlUploadPhotoUri.toString())
                viewModel.insertNewAccountDataInDatabase()
                vp?.currentItem = 3
            }
            else Toast.makeText(requireActivity(), "Выполнены не все условия", Toast.LENGTH_SHORT).show()
        }

        binding.ibPhotoExport.setOnClickListener {
            currentPhotoType = PhotoType.AVATAR
            openGallery()
        }

        binding.llDLUploadPhoto.setOnClickListener {
            currentPhotoType = PhotoType.DL_UPLOAD
            openGallery()
        }

        binding.llPassportUploadPhoto.setOnClickListener {
            currentPhotoType = PhotoType.PASSPORT
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

    private val galleryResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val selectedUri = data?.data
            when (currentPhotoType) {
                PhotoType.AVATAR -> avatarPhotoUri = selectedUri
                PhotoType.DL_UPLOAD -> dlUploadPhotoUri = selectedUri
                PhotoType.PASSPORT -> passportUploadPhotoUri = selectedUri
                else -> throw IllegalArgumentException("Unknown photo type: $currentPhotoType")
            }
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
                // Format and set the date if valid
                val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
                targetEditText.setText(formattedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }

}