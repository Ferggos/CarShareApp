package com.example.testapp.domain.favorites

import com.example.testapp.data.tables.AvailableCarsDto
import com.example.testapp.data.tables.FavoriteCarDto
import com.example.testapp.domain.availableCars.AvailableCar

interface FavoriteRepository {
        suspend fun getCars(): List<FavoriteCarDto>?
        suspend fun getCar(id: Int): AvailableCarsDto
        suspend fun inputFavorite(availableCar: AvailableCar): Boolean
        suspend fun deleteProduct(id: String)
}