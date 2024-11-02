package com.example.testapp.screens.Welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testapp.screens.AuthorizationActivity
import com.example.testapp.databinding.FragmentWelcome3Binding


class WelcomeFragment3 : Fragment() {



    private var _binding: FragmentWelcome3Binding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityNoconnectionBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcome3Binding.inflate(inflater, container, false)

        binding.btnGo.setOnClickListener {
            (activity as WelcomeActivity?)?.saveToken()
            val intent = Intent(requireActivity(), AuthorizationActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = com.example.testapp.screens.Welcome.WelcomeFragment3()
    }
}