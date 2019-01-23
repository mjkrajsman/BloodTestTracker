package com.mjkrajsman.bloodtesttracker

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mjkrajsman.bloodtesttracker.recycler.PatientAdapter
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.viewmodel.PatientListViewModel
import kotlinx.android.synthetic.main.activity_patient_list.*

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientListActivity : AppCompatActivity() {
    //---===ViewModel===---
    private val viewModel: PatientListViewModel by lazy { ViewModelProviders.of(this).get(PatientListViewModel::class.java) }

    //---===RecyclerView===---
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    //TODO: after providing normal data source - replace viewModel.getRandomPatientList() with getPatientList()
    private val viewAdapter by lazy { PatientAdapter(viewModel.getRandomPatientList(), this::showPatientActivity)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_list)
        setSupportActionBar(patient_list_toolbar)

        patient_list_fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get toolbar color from intent
        patient_list_toolbar.setBackgroundColor(extractColorFromIntent())

        //---===RecyclerView===---
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.patient_list_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    //---===data extraction from intent===---
    private fun extractColorFromIntent(): Int {
        return intent.getIntExtra("color", Color.BLACK)
    }

    //---===data input to intent===---
    //TODO: store and pass data in another way? PatientItem should be accessible by ViewModel.
    private fun showPatientActivity(patientItem: PatientItem) {
        val intent = Intent(this, PatientActivity::class.java)
            .putExtra("color", viewModel.getRandomColor())
            .putExtra("patientItem", patientItem)
        startActivity(intent)
    }

}