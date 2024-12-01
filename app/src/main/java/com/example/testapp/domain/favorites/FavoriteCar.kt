package com.example.testapp.domain.favorites

data class FavoriteCar(
    val id: Int,
    val brand: String,
    val model: String,
    val transmissionType: String,
    val engineType: String,
    val price: Int,
    val imageLogo: String,
    val address: String,
    val description: String,
    val imagePhoto: String,
    val carId: Int
)
