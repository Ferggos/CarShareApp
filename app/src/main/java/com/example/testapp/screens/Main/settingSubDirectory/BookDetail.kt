package com.example.testapp.screens.Main.settingSubDirectory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.databinding.FragmentBookDetailBinding
import com.example.testapp.screens.Main.Settings
import com.example.testapp.screens.Main.MainActivity
import kotlinx.coroutines.launch
import kotlin.getValue

class BookDetail : Fragment() {

    private var _binding: FragmentBookDetailBinding? = null
    private val binding get() = _binding!!

    private var carId: Int = -1 // id автомобиля

    private val bookViewModel: UserBookViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Получаем id автомобиля из аргументов
        arguments?.let {
            carId = it.getInt("CAR_ID", -1) // Получаем id автомобиля
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<LinearLayout>(R.id.llNav)?.visibility = View.GONE

        // Запускаем корутину для сбора данных из StateFlow
        viewLifecycleOwner.lifecycleScope.launch {
            bookViewModel.carsList.collect { cars ->
                // Найдем машину по id
                val car = cars?.find { it.id == carId }
                car?.let {
                    // Заполняем данные на экране
                    binding.tvWelcomeText.text = "Бронирование #${it.id}"
                    binding.carName.text = it.model
                    binding.carBrand.text = it.brand
                    binding.carPrice.text = "${it.price}₽ в день"
                    Glide.with(binding.carImage.context)
                        .load(it.imageLogo)
                        .into(binding.carImage)
                    binding.tvAddress.text = it.address
                    binding.tvStartDate.text = it.startDate
                    binding.tvEndDate.text = it.endDate
                    binding.tvUserData.text = "Иван Иванов"
                    binding.tvDLNumber.text = "456134"
                    binding.tvStatus.text = it.status
                    binding.tvRentPrice.text = "${it.price}₽/день"
                    binding.tvFinalSum.text = "${it.price * 3}₽"

                }
            }
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookDetailBinding.inflate(inflater, container, false)

        val mainActivity = requireActivity() as MainActivity

        binding.ibBack.setOnClickListener {
            activity?.findViewById<LinearLayout>(R.id.llNav)?.visibility = View.VISIBLE
            mainActivity.navigateToFragment(UserBooking()) // Переход на Favorites
        }

        binding.btnCancel.setOnClickListener{
            viewLifecycleOwner.lifecycleScope.launch {
                bookViewModel.updateStatus(carId, "Отмена")
            }
            mainActivity.navigateToFragment(UserBooking())
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
        fun newInstance(carId: Int): BookDetail {
            val fragment = BookDetail()
            val args = Bundle()
            args.putInt("CAR_ID", carId)
            fragment.arguments = args
            return fragment
        }
    }
}