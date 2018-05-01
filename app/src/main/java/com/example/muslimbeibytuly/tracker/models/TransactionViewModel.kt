package com.example.muslimbeibytuly.tracker.models

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.app.Application


class TransactionViewModel: AndroidViewModel {

    private var mRepository: TransactionRepository
    private var mAllTransactions: LiveData<List<NewTransaction>>

    constructor(application: Application) : super(application) {
        mRepository = TransactionRepository(application)
        mAllTransactions = mRepository.getAllTransactions()
    }

    public fun insert(transaction: NewTransaction) {
        mRepository.insert(transaction)
    }

    public fun getAllTransactions(): LiveData<List<NewTransaction>> {
        return mAllTransactions
    }

}
