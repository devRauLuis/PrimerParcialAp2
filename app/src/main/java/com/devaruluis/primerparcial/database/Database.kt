package com.devaruluis.loanscompose.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class Database : RoomDatabase() {
//    abstract fun getDao(): Dao
}