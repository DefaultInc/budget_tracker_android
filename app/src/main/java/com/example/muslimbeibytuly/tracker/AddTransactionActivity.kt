package com.example.muslimbeibytuly.tracker

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import com.example.muslimbeibytuly.tracker.models.NewTransaction
import com.example.muslimbeibytuly.tracker.models.TransactionViewModel

class AddTransactionActivity : AppCompatActivity() {

    private var mTransactionViewModel: TransactionViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)
        this.supportActionBar?.title = "Add Transaction"
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setDisplayShowHomeEnabled(true);

        mTransactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel::class.java)

        val addButton = findViewById(R.id.add_button) as Button
        addButton.setOnClickListener({
            if (validateInput()) {
                addTransaction()
            }
        })
    }

    private fun validateInput(): Boolean {
        var isValid = true
        val nameInput = findViewById(R.id.add_name) as TextInputEditText
        if (nameInput.text.isEmpty()) {
            nameInput.error = "Please enter name"
            isValid = false
        }
        val amountInput = findViewById(R.id.add_input_amount) as TextInputEditText
        if (amountInput.text.isEmpty()) {
            amountInput.error = "Please set positive amount"
            isValid = false
        }
        return isValid
    }

    private fun addTransaction() {
        val nameInput = findViewById(R.id.add_name) as TextInputEditText
        val amountInput = findViewById(R.id.add_input_amount) as TextInputEditText
        val name = nameInput.text.toString()
        val amount = amountInput.text.toString()
        val transaction = NewTransaction(name, amount.toLong())

        mTransactionViewModel?.insert(transaction)
        onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
