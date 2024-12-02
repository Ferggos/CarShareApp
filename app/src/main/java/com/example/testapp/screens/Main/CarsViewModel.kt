package com.example.testapp.screens.Main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.tables.AvailableCarsDto
import com.example.testapp.domain.availableCars.AvailableCar
import com.example.testapp.domain.availableCars.CarsRepository
import com.example.testapp.domain.favorites.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val carsRepository: CarsRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private var _carsList = MutableStateFlow<List<AvailableCar>?>(listOf())
    val carsList: Flow<List<AvailableCar>?> = _carsList

    init {
        getCars()
    }

    fun getCars() {
        viewModelScope.launch {
            val cars = carsRepository.getCars()
            _carsList.emit(cars?.map { it -> it.asDomainModel() })
        }
    }

    fun getCarById(id: Int): AvailableCar? {
        return _carsList.value?.find { it.id == id }
    }

    private fun AvailableCarsDto.asDomainModel(): AvailableCar {
        return AvailableCar(
            id = this.id,
            brand = this.brand,
            model = this.model,
            transmissionType = this.transmissionType,
            engineType = this.engineType,
            price = this.price,
            imageLogo = this.imageLogo,
            address = this.address,
            description = this.description,
            imagePhoto = this.imagePhoto
        )
    }
}
