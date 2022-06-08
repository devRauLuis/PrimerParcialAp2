package com.devaruluis.primerparcial.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "loans")
data class Loan(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "debtorName")
    val debtorName: String?,
    @ColumnInfo(name = "concept")
    val concept: String?,
    @ColumnInfo(name = "amount")
    val amount: Float?,
)