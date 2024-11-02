package com.example.testapp.screens.Welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.testapp.R
import com.example.testapp.databinding.FragmentWelcome2Binding


class WelcomeFragment2 : Fragment() {



    private var _binding: FragmentWelcome2Binding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityNoconnectionBinding must not be null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWelcome2Binding.inflate(inflater, container, false)

        val vp = activity?.findViewById<ViewPager2>(R.id.vp2)

        binding.btnNext.setOnClickListener {
            vp?.currentItem = 2
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = WelcomeFragment2()
    }
}