package com.example.muslimbeibytuly.tracker

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import com.example.muslimbeibytuly.tracker.adapters.TransactionsListViewAdapter
import com.example.muslimbeibytuly.tracker.models.NewTransaction
//import com.example.muslimbeibytuly.tracker.models.Transaction
import com.example.muslimbeibytuly.tracker.models.TransactionViewModel
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_master.*
import kotlinx.android.synthetic.main.app_bar_master.*


class MasterActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mTransactionViewModel: TransactionViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master)
        setSupportActionBar(toolbar)

        mTransactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel::class.java)
        /**
         * graph initialization
         */
        val graph = findViewById<View>(R.id.graph) as GraphView
        val series = LineGraphSeries<DataPoint>(
                arrayOf(
                        DataPoint(0.0, 1.0),
                        DataPoint(1.0, 5.0),
                        DataPoint(2.0, 3.0),
                        DataPoint(3.0, 2.0),
                        DataPoint(4.0, 6.0)
                )
        )

        mTransactionViewModel?.getAllTransactions()?.observe(this, Observer<List<NewTransaction>>() {
            val adapter = TransactionsListViewAdapter(this, it as ArrayList<NewTransaction>)
            val transactionsListView = findViewById<ListView>(R.id.transactionsListView)
            transactionsListView.transcriptMode = ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL
            transactionsListView.adapter = adapter
        })


        /**
         * camera image capture activity
         * */
        fab.setOnClickListener { view ->
            val intent = Intent(this, CameraActivity()::class.java)
            startActivity(intent)
        }
        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.master, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
