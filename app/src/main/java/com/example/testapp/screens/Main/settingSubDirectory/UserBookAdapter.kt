package com.example.testapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.databinding.UserBookingItemBinding
import com.example.testapp.domain.availableCars.AvailableCar
import com.example.testapp.domain.userBooks.BookCar
import com.example.testapp.screens.Main.Details
import com.example.testapp.screens.Main.MainActivity
import com.example.testapp.screens.Main.settingSubDirectory.BookDetail

class UserBookAdapter(
    var cars: List<BookCar>,
    private val mainActivity: MainActivity
) : RecyclerView.Adapter<UserBookAdapter.CarViewHolder>() {

    inner class CarViewHolder(private val binding: UserBookingItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(car: BookCar) {
            binding.tvBrandModel.text = "${car.brand} ${car.model}"
            binding.carRentStatus.text = when (car.status) {
                "Одобрено" -> "Начало аренды: ${car.startDate}"
                "Отмена" -> "Аренда отменена: ${car.startDate}"
                else -> "Аренда завершена: ${car.startDate}"
            }
            binding.llBookCarItem.setOnClickListener {
                val carId = car.id // Получаем id автомобиля
                val bookDetailFragment = BookDetail.newInstance(carId) // Передаем только id
                mainActivity.navigateToFragment(bookDetailFragment) // Переход на фрагмент с деталями
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = UserBookingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])

    }

    override fun getItemCount() = cars.size
}
