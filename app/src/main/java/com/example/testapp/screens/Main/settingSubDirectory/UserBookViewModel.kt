package com.example.testapp.screens.Main.settingSubDirectory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.tables.AvailableCarsDto
import com.example.testapp.data.tables.UserBooksDto
import com.example.testapp.domain.availableCars.AvailableCar
import com.example.testapp.domain.availableCars.CarsRepository
import com.example.testapp.domain.favorites.FavoriteRepository
import com.example.testapp.domain.userBooks.BookCar
import com.example.testapp.domain.userBooks.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserBookViewModel @Inject constructor(
    private val bookRepository: BookRepository,
) : ViewModel() {

    private var _carsList = MutableStateFlow<List<BookCar>?>(listOf())
    val carsList: Flow<List<BookCar>?> = _carsList

    init {
        getCars()
    }

    fun getCars() {
        viewModelScope.launch {
            val cars = bookRepository.getCars()
            _carsList.emit(cars?.map { it -> it.asDomainModel() })
        }
    }

    fun getCarById(id: Int): BookCar? {
        return _carsList.value?.find { it.id == id }
    }

    private fun UserBooksDto.asDomainModel(): BookCar {
        return BookCar(
            id = this.id,
            brand = this.brand,
            model = this.model,
            price = this.price,
            imageLogo = this.imageLogo,
            address = this.address,
            status = this.status,
            startDate = this.startDate,
            endDate = this.endDate
        )
    }
}