package com.example.testapp.data.tables

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UserBooksDto(
    @SerialName("id")
    val id: Int,
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

