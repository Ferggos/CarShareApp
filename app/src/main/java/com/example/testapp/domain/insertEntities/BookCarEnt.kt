package com.example.testapp.domain.insertEntities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookCarEnt(
    @SerialName("brand")
    val brand: String,
    @SerialName("model")
    val model: String,
    @SerialName("price")
    val price: Int,
    @SerialName("image_logo")
    val imageLogo: String,
    @SerialName("address")
    val address: String,
    @SerialName("status")
    val status: String,
    @SerialName("start_date")
    val startDate: String,
    @SerialName("end_date")
    val endDate: String
)
