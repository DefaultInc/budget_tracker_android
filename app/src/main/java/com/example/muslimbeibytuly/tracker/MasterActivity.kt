package com.example.muslimbeibytuly.tracker

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil.dip2px
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper
import com.wangjie.rapidfloatingactionbutton.util.RFABTextUtil.dip2px
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout







class MasterActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private var mTransactionViewModel: TransactionViewModel? = null

    // Fabs
    private var isFabOpen = false;
    private var fab: FloatingActionButton? = null
    private var fabWrite: FloatingActionButton? = null
    private var fabCamera: FloatingActionButton? = null
    private var fab_open: Animation? = null
    private var fab_close: Animation? = null
    private var rotate_forward: Animation? = null
    private var rotate_backward: Animation? = null

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

        series.color =  Color.RED
        series.backgroundColor = Color.rgb(255, 130, 149)
        series.isDrawBackground = true
        graph.addSeries(series)

        mTransactionViewModel?.getAllTransactions()?.observe(this, Observer<List<NewTransaction>>() {
            val adapter = TransactionsListViewAdapter(this, it as ArrayList<NewTransaction>)
            val transactionsListView = findViewById<ListView>(R.id.transactionsListView)
            transactionsListView.transcriptMode = ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL
            transactionsListView.adapter = adapter
        })


        val toggle = ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        buildFabs()
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

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.fab ->
                animateFAB()
            R.id.fab_write-> {
                /**
                 * Add transaction activity.
                 **/
                val intent = Intent(this, AddTransactionActivity()::class.java)
                startActivity(intent)
            }
            R.id.fab_camera-> {
                /**
                 * Camera image capture activity.
                 **/
                val intent = Intent(this, CameraActivity()::class.java)
                startActivity(intent)
            }
        }
    }

    fun animateFAB(){
        if(isFabOpen){
            fab?.startAnimation(rotate_backward);
            fabWrite?.startAnimation(fab_close);
            fabCamera?.startAnimation(fab_close);
            fabWrite?.setClickable(false);
            fabCamera?.setClickable(false);
            isFabOpen = false;
        } else {
            fab?.startAnimation(rotate_forward);
            fabWrite?.startAnimation(fab_open);
            fabCamera?.startAnimation(fab_open);
            fabWrite?.setClickable(true);
            fabCamera?.setClickable(true);
            isFabOpen = true;
        }
    }


    fun buildFabs() {
        fab = findViewById(R.id.fab) as FloatingActionButton
        fabWrite = findViewById(R.id.fab_write) as FloatingActionButton
        fabCamera = findViewById(R.id.fab_camera) as FloatingActionButton
        fab_open = AnimationUtils.loadAnimation(this.applicationContext, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this.applicationContext,R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(this.applicationContext,R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(this.applicationContext,R.anim.rotate_backward);
        fab?.setOnClickListener(this)
        fabWrite?.setOnClickListener(this)
        fabCamera?.setOnClickListener(this)
    }

}
