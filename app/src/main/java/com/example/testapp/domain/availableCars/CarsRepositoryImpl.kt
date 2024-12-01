package com.example.testapp.domain.availableCars

import android.util.Log
import com.example.testapp.data.tables.AvailableCarsDto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage
) : CarsRepository {
    override suspend fun getCars() : List<AvailableCarsDto>?
    {
        return withContext(Dispatchers.IO) {
            Log.d("Supabase", "Executing query to fetch available cars")
            val result = postgrest.from("available_cars")
                .select().decodeList<AvailableCarsDto>()
            Log.d("Supabase", "Result: $result, size: ${result.size}")
            result
        }
    }
    override suspend fun getCar(id: Int): AvailableCarsDto {
        return withContext(Dispatchers.IO) {
            postgrest.from("available_cars").select {
                filter {
                    eq("id", id)
                }
            }.decodeSingle<AvailableCarsDto>()
        }
    }

}