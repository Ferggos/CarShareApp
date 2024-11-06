package com.example.testapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.model.Car
import com.example.testapp.databinding.ItemCarBinding

class CarAdapter(private val cars: List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    inner class CarViewHolder(private val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(car: Car) {
            binding.carName.text = car.name
            binding.carBrand.text = car.brand
            binding.carPrice.text = car.pricePerDay
            binding.carTransmissionFuel.text = car.transmissionFuel
            binding.carImage.setImageResource(car.imageResource)

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
