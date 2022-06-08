package com.devaruluis.loanscompose.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devaruluis.loanscompose.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val LOANS_DATABASE_NAME = "loans_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        Database::class.java, LOANS_DATABASE_NAME
    ).fallbackToDestructiveMigration().setQueryCallback(
        RoomDatabase.QueryCallback
        { sqlQuery, bindArgs ->
            println("SQL Query: $sqlQuery SQL Args: $bindArgs")
        }, Executors.newSingleThreadExecutor()
    ).build()

//    @Provides
//    fun provideDao(database: Database) = database.getDao()

}