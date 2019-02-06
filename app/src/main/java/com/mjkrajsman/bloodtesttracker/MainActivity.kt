package com.mjkrajsman.bloodtesttracker

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.mjkrajsman.bloodtesttracker.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by: Maciej Janusz Krajsman
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val viewModel: MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)

        //JodaTime initialization for app
        JodaTimeAndroid.init(this)

        main_fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        main_toolbar.setBackgroundColor(viewModel.getRandomColor())

        //---===DrawerLayout===---
        val toggle = ActionBarDrawerToggle(
            this, main_drawer_layout, main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        main_drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        main_nav_view.setNavigationItemSelectedListener(this)

        //---===Other layout item listeners===---

        //button listeners TODO: replace with final content of MainActivity
        button_patient_list.setOnClickListener(this::showPatientListActivity)
    }

    //---===DrawerLayout actions===--- TODO: implement those
    override fun onBackPressed() {
        if (main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            main_drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
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

        main_drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    //---===data input to intent===---
    private fun showPatientListActivity(view: View) {
        val intent = Intent(this, PatientListActivity::class.java)
            .putExtra("color", viewModel.getRandomColor())
        startActivity(intent)
    }
}
