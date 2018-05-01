package com.example.muslimbeibytuly.tracker.models

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.content.Context
import com.firebase.ui.auth.AuthUI.getApplicationContext
import android.arch.persistence.room.Room





@Database(entities = arrayOf(NewTransaction::class), version = 1)
abstract class TransactionRoomDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object : SingletonHolder<TransactionRoomDatabase, Context>({
        Room.databaseBuilder(it.applicationContext,
                TransactionRoomDatabase::class.java, "transaction_database")
                .build()
    })
}

open class SingletonHolder<out T, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}
