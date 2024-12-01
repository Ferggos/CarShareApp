package com.example.testapp.domain.availableCars

import com.example.testapp.data.tables.AvailableCarsDto

interface CarsRepository {
    suspend fun getCars(): List<AvailableCarsDto>?
    suspend fun getCar(id: Int): AvailableCarsDto
}