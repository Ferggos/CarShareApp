package com.example.testapp.screens.Main.settingSubDirectory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.adapters.UserBookAdapter
import com.example.testapp.databinding.FragmentUserBookingBinding
import com.example.testapp.screens.Main.MainActivity
import com.example.testapp.screens.Main.Settings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class UserBooking : Fragment() {

    private var _binding: FragmentUserBookingBinding? = null
    private val binding get() = _binding!!

    private val bookViewModel by activityViewModels<UserBookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBookingBinding.inflate(inflater, container, false)
        return binding.root
        return inflater.inflate(R.layout.fragment_user_booking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvCar.layoutManager = LinearLayoutManager(requireContext())
        val mainActivity = activity as MainActivity

        binding.ibBack.setOnClickListener{
            mainActivity.navigateToFragment(Settings())
        }

        // Собираем Flow и обновляем адаптер, когда данные изменяются
        lifecycleScope.launch {
            bookViewModel.carsList.collectLatest { cars ->
                cars?.let {
                    binding.rvCar.adapter = UserBookAdapter(it, mainActivity)
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = UserBooking()
    }
}