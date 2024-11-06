package com.example.testapp.screens.Registration

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapp.screens.Main.MainActivity
import com.example.testapp.databinding.FragmentCompleteRegistrationBinding

class CompleteRegistrationFragment : Fragment() {

    private var _binding: FragmentCompleteRegistrationBinding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityNoconnectionBinding must not be null")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompleteRegistrationBinding.inflate(layoutInflater)
        (activity as? RegistrationActivity)?.setLinearLayoutVisibility(false)

        binding.btnNext.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = CompleteRegistrationFragment()
    }
}