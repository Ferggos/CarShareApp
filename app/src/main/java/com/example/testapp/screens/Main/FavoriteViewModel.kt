package com.example.testapp.screens.Main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.tables.FavoriteCarDto
import com.example.testapp.domain.availableCars.AvailableCar
import com.example.testapp.domain.favorites.FavoriteCar
import com.example.testapp.domain.favorites.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private var _carsList = MutableStateFlow<List<FavoriteCar>?>(listOf())
    val carsList: Flow<List<FavoriteCar>?> = _carsList

    init {
        getCars()
    }

    fun getCars() {
        viewModelScope.launch {
            val cars = favoriteRepository.getCars()
            _carsList.emit(cars?.map { it -> it.asDomainModel() })
        }
    }

    fun getCarById(id: Int): FavoriteCar? {
        return _carsList.value?.find { it.id == id }
    }

    fun addCarToFavorites(car: AvailableCar) {
        viewModelScope.launch {
            try {
                Log.d("Info", "Using repository Favorites insert")
                favoriteRepository.inputFavorite(car)
                getCars() // Обновляем список после добавления
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }
    }

    fun removeCarFromFavorites(id: String) {
        viewModelScope.launch {
            try {
                favoriteRepository.deleteProduct(id)
                getCars() // Обновляем список после удаления
            } catch (e: Exception) {
                Log.d("Error", e.toString())
            }
        }
    }

    private fun FavoriteCarDto.asDomainModel(): FavoriteCar {
        return FavoriteCar(
            id = this.id,
            brand = this.brand,
            model = this.model,
            transmissionType = this.transmissionType,
            engineType = this.engineType,
            price = this.price,
            imageLogo = this.imageLogo,
            address = this.address,
            description = this.description,
            imagePhoto = this.imagePhoto,
            carId = this.carId
        )
    }
}
