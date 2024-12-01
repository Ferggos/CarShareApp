package com.example.testapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.databinding.ItemCarBinding
import com.example.testapp.domain.availableCars.AvailableCar
import com.example.testapp.screens.Main.BookingCar
import com.example.testapp.screens.Main.CarsViewModel
import com.example.testapp.screens.Main.Details
import com.example.testapp.screens.Main.MainActivity

class CarAdapter(
    var cars: List<AvailableCar>,
    private val mainActivity: MainActivity
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    inner class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {
        val detailsButton: Button = itemView.findViewById(R.id.detailsButton)
        fun bind(car: AvailableCar) {
            Log.d("CarAdapter", "Binding data: ${car.model}, ${car.brand}, ${car.price}, ${car.transmissionType}, ${car.engineType}, ${car.imageLogo}")
            binding.carName.text = car.model
            binding.carBrand.text = car.brand
            binding.carPrice.text = "${car.price.toString()}₽ в день"
            binding.carTransmissionFuel.text = "${car.transmissionType} - ${car.engineType}"
            Glide.with(binding.carImage.context)
                .load(car.imageLogo) // URL изображения
                .into(binding.carImage)

            binding.detailsButton.setOnClickListener {
                val carId = car.id // Получаем id автомобиля
                val detailsFragment = Details.newInstance(carId, "HomePage") // Передаем только id
                mainActivity.navigateToFragment(detailsFragment) // Переход на фрагмент с деталями
            }

            binding.bookButton.setOnClickListener {
                val carId = car.id // Получаем id автомобиля
                val bookingFragment = BookingCar.newInstance(carId) // Передаем только id
                mainActivity.navigateToFragment(bookingFragment) // Переход на фрагмент с деталями
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])

    }

    override fun getItemCount() = cars.size
}
