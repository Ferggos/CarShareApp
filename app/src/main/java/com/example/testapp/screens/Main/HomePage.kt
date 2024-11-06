package com.example.testapp.screens.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.adapters.CarAdapter
import com.example.testapp.databinding.FragmentHomePageBinding
import com.example.testapp.model.Car

class HomePage : Fragment() {

    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cars = listOf(
            Car("S 500 Sedan", "Mercedes-Benz", "2500₽ в день", "A/T • Бензин", R.drawable.mercdes_png),
            Car("Model X", "Tesla", "3000₽ в день", "A/T • Electric", R.drawable.mercdes_png),
            Car("A4", "Audi", "2000₽ в день", "A/T • Бензин", R.drawable.mercdes_png),
            Car("S 500 Sedan", "Mercedes-Benz", "2500₽ в день", "A/T • Бензин", R.drawable.mercdes_png)
        )

        binding.rvCar.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCar.adapter = CarAdapter(cars)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomePage()
    }
}
