package com.example.testapp.screens.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.testapp.R
import com.example.testapp.databinding.FragmentSuccessBookBinding
import com.example.testapp.screens.Main.settingSubDirectory.UserBooking

class SuccessBook : Fragment() {

    private var _binding: FragmentSuccessBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.findViewById<LinearLayout>(R.id.llNav)?.visibility = View.GONE
        _binding = FragmentSuccessBookBinding.inflate(inflater, container, false)
        val mainActivity = requireActivity() as MainActivity



        binding.btnGoToUserBooks.setOnClickListener{
            mainActivity.navigateToFragment(UserBooking())
            activity?.findViewById<LinearLayout>(R.id.llNav)?.visibility = View.VISIBLE
        }

        binding.btnHomePage.setOnClickListener{
            mainActivity.navigateToFragment(HomePage())
            activity?.findViewById<LinearLayout>(R.id.llNav)?.visibility = View.VISIBLE
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.findViewById<LinearLayout>(R.id.llNav)?.visibility = View.VISIBLE
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = SuccessBook()
    }
}