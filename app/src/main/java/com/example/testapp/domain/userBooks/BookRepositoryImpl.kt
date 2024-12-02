package com.example.testapp.domain.userBooks

import android.util.Log
import com.example.testapp.data.tables.AvailableCarsDto
import com.example.testapp.data.tables.UserBooksDto
import com.example.testapp.domain.availableCars.AvailableCar
import com.example.testapp.domain.insertEntities.AvailableCarEnt
import com.example.testapp.domain.insertEntities.BookCarEnt
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage
) : BookRepository {
    override suspend fun getCars() : List<UserBooksDto>?
    {
        return withContext(Dispatchers.IO) {
            Log.d("Supabase", "Executing query to fetch available cars")
            val result = postgrest.from("user_books")
                .select().decodeList<UserBooksDto>()
            Log.d("Supabase", "Result: $result, size: ${result.size}")
            result
        }
    }
    override suspend fun getCar(id: Int): UserBooksDto {
        return withContext(Dispatchers.IO) {
            postgrest.from("user_books").select {
                filter {
                    eq("id", id)
                }
            }.decodeSingle<UserBooksDto>()
        }
    }

    override suspend fun inputBook(bookCar: BookCar): Boolean{
        return try {
            withContext(Dispatchers.IO) {
                Log.d("Supabase", "Inserting of data")
                // Отправляем на сервер
                val car = BookCarEnt(
                    brand = bookCar.brand,
                    model = bookCar.model,
                    price = bookCar.price,
                    imageLogo = bookCar.imageLogo,
                    address = bookCar.address,
                    status = bookCar.status,
                    startDate = bookCar.startDate,
                    endDate = bookCar.endDate
                )
                postgrest.from("user_books").insert(car)
                true
            }
            true
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    override suspend fun deleteProduct(id: String) {
        return withContext(Dispatchers.IO) {
            Log.d("Supabase", "Executing query to fetch available cars")
            postgrest.from("user_books").delete {
                filter {
                    eq("id", id)
                }
            }
        }
    }

    override suspend fun updateProduct(id: Int, status: String) {
        return withContext(Dispatchers.IO) {
            Log.d("Supabase", "Updating product")
            postgrest.from("user_books").update({
                set("status", status)
            }) {
                filter {
                    eq("id", id)
                }

            }
        }
    }


}