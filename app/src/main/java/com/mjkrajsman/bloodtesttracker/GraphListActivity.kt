package com.mjkrajsman.bloodtesttracker

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.recycler.GraphListAdapter
import com.mjkrajsman.bloodtesttracker.viewmodel.GraphListViewModel
import com.mjkrajsman.bloodtesttracker.viewmodel.GraphListViewModelFactory
import kotlinx.android.synthetic.main.activity_graph_list.*

/**
 * Created by: Maciej Janusz Krajsman
 */
class GraphListActivity : AppCompatActivity() {
    private val viewModel:GraphListViewModel by lazy { ViewModelProviders.of(this,
        GraphListViewModelFactory(this.application, extractPatientItemFromIntent())
    ).get(GraphListViewModel::class.java) }

    //---===RecyclerView===---
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val viewAdapter by lazy { GraphListAdapter(this::showGraphActivity, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_list)
        setSupportActionBar(graph_list_toolbar)
        graph_list_fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toolbarColor = viewModel.getRandomColor()
        graph_list_toolbar.setBackgroundColor(toolbarColor)
        graph_list_toolbar_layout.setBackgroundColor(toolbarColor)

        //---===RecyclerView===---
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.graph_list_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        viewModel.tests.observe(this, Observer { items ->
            items?.let { viewAdapter.setItems(it) }
        })
    }

    override fun onStart() {
        super.onStart()
        updateTitle(viewModel.patientItem!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun updateTitle(item: PatientItem) {
        if(item.secondName!=null) {
            graph_list_toolbar_layout.title = getString(
                R.string.patient_title_name_template, item.firstName, item.secondName, item.surname)
        }else{
            graph_list_toolbar_layout.title = getString(
                R.string.patient_title_name_short_template, item.firstName, item.surname)
        }
    }

    //---===data extraction from intent===---
    private fun extractPatientItemFromIntent(): PatientItem? {
        return intent.getSerializableExtra("patientItem") as? PatientItem
    }

    //---===data input to intent===---
    private fun showGraphActivity(test: String) {
        val intent = Intent(this, GraphActivity::class.java)
            //.putExtra("patientId", viewModel.patientItem?.id)
            .putExtra("patientItem", viewModel.patientItem)
            .putExtra("testType", test)
        startActivity(intent)
    }

}
