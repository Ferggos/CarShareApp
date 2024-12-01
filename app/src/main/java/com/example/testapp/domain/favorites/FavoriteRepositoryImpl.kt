package com.example.testapp.domain.favorites

import android.util.Log
import com.example.testapp.data.tables.AvailableCarsDto
import com.example.testapp.data.tables.FavoriteCarDto
import com.example.testapp.domain.availableCars.AvailableCar
import com.example.testapp.domain.insertEntities.AvailableCarEnt
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage
) : FavoriteRepository {
    override suspend fun getCars() : List<FavoriteCarDto>?
    {
        return withContext(Dispatchers.IO) {
            Log.d("Supabase", "Executing query to fetch available cars")
            val result = postgrest.from("favorite_cars")
                .select().decodeList< FavoriteCarDto>()
            Log.d("Supabase", "Result: $result, size: ${result.size}")
            result
        }
    }

    override suspend fun getCar(id: Int): AvailableCarsDto {
        return withContext(Dispatchers.IO) {
            postgrest.from("favorite_cars").select {
                filter {
                    eq("id", id)
                }
            }.decodeSingle<AvailableCarsDto>()
        }
    }

    override suspend fun inputFavorite(availableCar: AvailableCar): Boolean{
        return try {
            withContext(Dispatchers.IO) {
                Log.d("Supabase", "Inserting of data")
                // Отправляем на сервер
                val car = AvailableCarEnt(
                    brand = availableCar.brand,
                    model = availableCar.model,
                    transmissionType = availableCar.transmissionType,
                    engineType = availableCar.engineType,
                    price = availableCar.price,
                    imageLogo = availableCar.imageLogo,
                    address = availableCar.address,
                    description = availableCar.description,
                    imagePhoto = availableCar.imagePhoto,
                    carId = availableCar.id
                )
                postgrest["favorite_cars"].insert(car)
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
            postgrest.from("favorite_cars").delete {
                filter {
                    eq("car_id", id)
                }
            }
        }
    }

}