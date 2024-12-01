package com.example.testapp.utils

import com.example.testapp.domain.availableCars.CarsRepository
import com.example.testapp.domain.availableCars.CarsRepositoryImpl
import com.example.testapp.domain.favorites.FavoriteRepository
import com.example.testapp.domain.favorites.FavoriteRepositoryImpl
import com.example.testapp.domain.userBooks.BookRepository
import com.example.testapp.domain.userBooks.BookRepositoryImpl


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCarsRepository(
        postgrest: Postgrest,
        storage: Storage
    ): CarsRepository {
        return CarsRepositoryImpl(postgrest, storage)
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(
        postgrest: Postgrest,
        storage: Storage
    ): FavoriteRepository {
        return FavoriteRepositoryImpl(postgrest, storage)
    }
    @Provides
    @Singleton
    fun provideUserBookRepository(
        postgrest: Postgrest,
        storage: Storage
    ): BookRepository {
        return BookRepositoryImpl(postgrest, storage)
    }
}
