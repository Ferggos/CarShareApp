package com.example.testapp.domain.availableCars

data class AvailableCar(
    val id: Int,
    val brand: String,
    val model: String,
    val transmissionType: String,
    val engineType: String,
    val price: Int,
    val imageLogo: String,
    val address: String,
    val description: String,
    val imagePhoto: String
)
