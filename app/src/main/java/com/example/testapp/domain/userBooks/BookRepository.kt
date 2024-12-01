package com.example.testapp.domain.userBooks

import com.example.testapp.data.tables.UserBooksDto


interface BookRepository {
    suspend fun getCars(): List<UserBooksDto>?
    suspend fun getCar(id: Int): UserBooksDto
    suspend fun inputBook(bookCar: BookCar): Boolean
    suspend fun deleteProduct(id: String)
}