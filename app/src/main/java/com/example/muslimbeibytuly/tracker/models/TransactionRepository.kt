package com.example.muslimbeibytuly.tracker.models

import android.os.AsyncTask
import android.arch.lifecycle.LiveData
import android.app.Application



class TransactionRepository {
    private var mTransactionDao: TransactionDao
    private var mAllTransactions: LiveData<List<NewTransaction>>


    constructor(application: Application) {
        val db = TransactionRoomDatabase.getInstance(application)
        mTransactionDao = db.transactionDao()
        mAllTransactions = mTransactionDao.getAllTransactions()
    }

    fun getAllTransactions(): LiveData<List<NewTransaction>> {
        return mAllTransactions
    }


    fun insert(transaction: NewTransaction) {
        insertAsyncTask(mTransactionDao).execute(transaction)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: TransactionDao) : AsyncTask<NewTransaction, Void, Void>() {

        override fun doInBackground(vararg params: NewTransaction): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}