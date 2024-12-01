package com.example.testapp.domain.insertEntities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableCarEnt(
    @SerialName("brand")
    val brand: String,
    @SerialName("model")
    val model: String,
    @SerialName("transmission_type")
    val transmissionType: String,
    @SerialName("engine_type")
    val engineType: String,
    @SerialName("price")
    val price: Int,
    @SerialName("image_logo")
    val imageLogo: String,
    @SerialName("address")
    val address: String,
    @SerialName("description")
    val description: String,
    @SerialName("image_photo")
    val imagePhoto: String,
    @SerialName("car_id")
    val carId: Int
)

