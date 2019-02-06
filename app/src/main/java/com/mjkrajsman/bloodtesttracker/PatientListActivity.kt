package com.mjkrajsman.bloodtesttracker

import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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
    private val viewAdapter by lazy { PatientAdapter(this::showPatientActivity, this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_list)
        setSupportActionBar(patient_list_toolbar)

        patient_list_fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        patient_list_toolbar.setBackgroundColor(viewModel.getRandomColor())

        //---===RecyclerView===---
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.patient_list_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewModel.patients.observe(this, Observer { items ->
            items?.let { viewAdapter.setItems(it) }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    //---===data input to intent===---
    private fun showPatientActivity(patientItem: PatientItem) {
        val intent = Intent(this, PatientActivity::class.java)
            .putExtra("patientItem", patientItem)
        startActivity(intent)
    }
}