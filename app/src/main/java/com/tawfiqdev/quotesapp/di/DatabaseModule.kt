package com.tawfiqdev.quotesapp.di

import android.content.Context
import androidx.room.Room
import com.tawfiqdev.quotesapp.data.room.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/***
 * Sert à expliquer à Hilt comment construire QuoteDatabase et QuoteDao.*
 * **/
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides //Créez l'objet vous-même
    fun provideQuoteDao(quoteDatabase: QuoteDatabase) = quoteDatabase.quoteDao()

    @Provides
    @Singleton
    fun provideQuoteDatabase(@ApplicationContext appContext: Context) : QuoteDatabase {
        return Room
            .databaseBuilder(appContext, QuoteDatabase::class.java, "quote_db")
            .fallbackToDestructiveMigration()
            .build()
    }
}