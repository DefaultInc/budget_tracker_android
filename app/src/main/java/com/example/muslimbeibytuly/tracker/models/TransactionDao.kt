package com.example.muslimbeibytuly.tracker.models

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Query
import android.arch.lifecycle.LiveData

@Dao
interface TransactionDao {

    @Insert
    fun insert(transaction: NewTransaction)

    @Delete
    fun deleteTask(task: NewTransaction)

    @Query("SELECT * from transaction_table ORDER BY date ASC")
    fun getAllTransactions(): LiveData<List<NewTransaction>>
}