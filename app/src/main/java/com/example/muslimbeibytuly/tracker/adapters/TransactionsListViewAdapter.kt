package com.example.muslimbeibytuly.tracker.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.muslimbeibytuly.tracker.R
import com.example.muslimbeibytuly.tracker.models.Transaction

class TransactionsListViewAdapter(private var activity: Activity, private var items: ArrayList<Transaction>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        var content: TextView?
        var price: TextView?

        init {
            this.content = row?.findViewById(R.id.content)
            this.price = row?.findViewById(R.id.price)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val viewHolder: ViewHolder

        if (convertView == null) {
            val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.transaction_card, parent, false)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val transaction = items[position]

        viewHolder.content?.text = transaction.content
        viewHolder.price?.text = transaction.price

        return view as View
    }

    override fun getItem(i: Int): Transaction {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}