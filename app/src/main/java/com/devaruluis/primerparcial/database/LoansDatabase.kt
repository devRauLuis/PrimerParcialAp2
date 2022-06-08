package com.devaruluis.loanscompose.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import com.devaruluis.primerparcial.database.dao.LoanDao
import com.devaruluis.primerparcial.model.Loan

@Database(entities = [Loan::class], version = 1)
abstract class LoansDatabase : RoomDatabase() {
    abstract fun getLoanDao(): LoanDao
}