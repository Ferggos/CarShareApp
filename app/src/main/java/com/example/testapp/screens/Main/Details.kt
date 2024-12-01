package com.example.testapp.screens.Main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.testapp.R
import com.example.testapp.databinding.FragmentDetailsBinding

import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.testapp.domain.availableCars.AvailableCar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Details : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private var carId: Int = -1 // id автомобиля
    private var source: String? = null

    private val carsViewModel: CarsViewModel by activityViewModels()
    private val favoritesViewModel: FavoriteViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Получаем id автомобиля из аргументов
        arguments?.let {
            carId = it.getInt("CAR_ID", -1) // Получаем id автомобиля
            source = it.getString("SOURCE") // Получаем источник вызова
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
                    binding.carTitle.text = "${it.brand} ${it.model}"
                    binding.textAddress.text = it.address
                    binding.textDescp.text = it.description
                    binding.tvPrice.text = "${it.price}₽ в день"
                    Glide.with(binding.carImage.context)
                        .load(it.imagePhoto)
                        .into(binding.carImage)

                    checkIfCarIsFavorite(it)
                }
            }
        }
    }

    private fun checkIfCarIsFavorite(car: AvailableCar) {
        viewLifecycleOwner.lifecycleScope.launch {
            favoritesViewModel.carsList.collect { favoriteCars ->
                favoriteCars?.let {
                    val isFavorite = it.any { favoriteCar ->
                        favoriteCar.carId == car.id
                    }
                    Log.d("isFavorite", isFavorite.toString())
                    binding.ibFavorite.apply {
                        setImageResource(
                            if (isFavorite) R.drawable.ic_favorite_pushed
                            else R.drawable.ic_favorite
                        )
                        tag = if (isFavorite) "favorite_pushed" else "favorite"
                    }
                } ?: run {
                    binding.ibFavorite.setImageResource(R.drawable.ic_favorite)
                    binding.ibFavorite.tag = "favorite"
                }
            }
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val mainActivity = requireActivity() as MainActivity

        binding.ibBack.setOnClickListener {
            if (source == "HomePage") {
                mainActivity.navigateToFragment(HomePage()) // Переход на HomePage
            } else if (source == "Favorites") {
                mainActivity.navigateToFragment(Favorites()) // Переход на Favorites
            }
        }

        binding.bookButton.setOnClickListener{
            mainActivity.navigateToFragment(BookingCar())
        }

        binding.ibFavorite.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                Log.d("Favorite", "Button clicked") // Лог нажатия
                carsViewModel.carsList.collectLatest { cars ->
                    Log.d("Favorite", "carsList collected: $cars") // Лог данных
                    val car = cars?.find { it.id == carId }
                    Log.d("Favorite", "Found car: $car") // Лог найденной машины
                    car?.let {
                        if (binding.ibFavorite.tag == "favorite") {
                            favoritesViewModel.addCarToFavorites(it)
                            binding.ibFavorite.setImageResource(R.drawable.ic_favorite_pushed)
                            binding.ibFavorite.tag = "favorite_pushed"
                        } else {
                            favoritesViewModel.removeCarFromFavorites(it.id.toString())
                            binding.ibFavorite.setImageResource(R.drawable.ic_favorite)
                            binding.ibFavorite.tag = "favorite"
                        }
                    }
                }
            }
        }





        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Очистка привязки при уничтожении представления
    }

    companion object {
        @JvmStatic
        fun newInstance(carId: Int, source: String): Details {
            val fragment = Details()
            val args = Bundle()
            args.putInt("CAR_ID", carId)
            args.putString("SOURCE", source) // Добавляем источник
            fragment.arguments = args
            return fragment
        }
    }
}



