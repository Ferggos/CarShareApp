package com.example.testapp.screens.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.R
import com.example.testapp.databinding.FragmentSettingsBinding


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

        val vp = activity?.findViewById<ViewPager2>(R.id.vp2)

        binding.llProfileSection.setOnClickListener{
            vp?.currentItem = 3
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = com.example.testapp.screens.Main.Settings()
    }
}