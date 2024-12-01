package com.example.testapp.screens.Main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.databinding.FragmentBookingCarBinding
import kotlinx.coroutines.launch

class BookingCar : Fragment() {

    private var _binding: FragmentBookingCarBinding? = null
    private val binding get() = _binding!!

    private var carId: Int = -1 // id автомобиля

    private val carsViewModel: CarsViewModel by activityViewModels() // Используем CarsViewModel для получения данных

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Получаем id автомобиля из аргументов
        arguments?.let {
            carId = it.getInt(ARG_CAR_ID, -1) // Получаем id автомобиля
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Запускаем корутину для сбора данных из StateFlow
        viewLifecycleOwner.lifecycleScope.launch {
            carsViewModel.carsList.collect { cars ->
                // Найдем машину по id
                val car = cars?.find { it.id == carId }
                car?.let {
                    // Заполняем данные на экране
                    binding.carName.text = it.model
                    binding.carBrand.text = it.brand
                    binding.carPrice.text = "${it.price}₽ в день"
                    binding.tvAddress.text = it.address
                    binding.tvRentPrice.text = "${it.price}₽/день"
                    binding.tvFinalSum.text = "${it.price*3}₽"
                    Glide.with(binding.carImage.context)
                        .load(it.imageLogo)
                        .into(binding.carImage)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         activity?.findViewById<LinearLayout>(R.id.llNav)?.visibility = View.GONE
        _binding = FragmentBookingCarBinding.inflate(inflater, container, false)
        val mainActivity = requireActivity() as MainActivity



        binding.ibBack.setOnClickListener{
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
        private const val ARG_CAR_ID = "car_id"

        @JvmStatic
        fun newInstance(carId: Int) = BookingCar().apply {
            arguments = Bundle().apply {
                putInt(ARG_CAR_ID, carId) // Сохраняем только id автомобиля
            }
        }
    }
}
