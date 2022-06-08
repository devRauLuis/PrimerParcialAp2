package com.devaruluis.primerparcial.database.dao

import androidx.room.*
import com.devaruluis.primerparcial.model.Loan

@Dao
interface LoanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(loan: Loan): Long

    @Update
    suspend fun update(loan: Loan): Int

    @Delete
    suspend fun delete(loan: Loan)

    @Query("SELECT * FROM loans WHERE id LIKE :id")
    suspend fun find(id: Long?): Loan?

    @Query("SELECT * FROM loans ORDER BY id")
    suspend fun getAll(): List<Loan>
}