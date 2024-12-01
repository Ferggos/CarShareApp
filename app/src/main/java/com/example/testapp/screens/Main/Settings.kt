package com.example.testapp.screens.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.databinding.FragmentSettingsBinding
import com.example.testapp.screens.Main.settingSubDirectory.Profile
import com.example.testapp.screens.Main.settingSubDirectory.UserBooking


class Settings : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private var _binding: FragmentSettingsBinding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityNoconnectionBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        val mainActivity = requireActivity() as MainActivity

        binding.llProfileSection.setOnClickListener{
            mainActivity.navigateToFragment(Profile())
        }

        binding.btnBookings.setOnClickListener{
            mainActivity.navigateToFragment(UserBooking())
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = com.example.testapp.screens.Main.Settings()
    }
}