package com.tawfiqdev.quotesapp.di

import com.tawfiqdev.quotesapp.data.service.QuoteDatabaseService
import com.tawfiqdev.quotesapp.data.service.QuoteDatabaseServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/***
 * Permet d'ecrire @Inject lateinit var service: QuoteDatabaseService
 * Hilt fournit un QuoteDatabaseServiceImpl configuré avec QuoteDao
 * Sans ce module, Hilt saurait instancier QuoteDatabaseServiceImpl,
 * mais il ne pourrait pas mapper vers QuoteDatabaseService
 * **/

@Module // Utilisée pour regrouper et fournir des dépendances
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds // Fournir une implémentation d'une interface.
    @Singleton
    abstract fun bindQuoteDatabaseService(
        impl: QuoteDatabaseServiceImpl
    ): QuoteDatabaseService
}