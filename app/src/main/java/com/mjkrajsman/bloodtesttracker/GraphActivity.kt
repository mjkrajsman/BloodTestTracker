package com.mjkrajsman.bloodtesttracker

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.mjkrajsman.bloodtesttracker.model.BloodTestItem
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.viewmodel.GraphViewModel
import com.mjkrajsman.bloodtesttracker.viewmodel.GraphViewModelFactory
import kotlinx.android.synthetic.main.activity_graph.*
import kotlinx.android.synthetic.main.content_graph.*
import java.util.*
import com.github.mikephil.charting.data.LineData






/**
 * Created by: Maciej Janusz Krajsman
 */
class GraphActivity : AppCompatActivity() {
        private val viewModel:GraphViewModel by lazy { ViewModelProviders.of(this,
            GraphViewModelFactory(this.application, extractPatientItemFromIntent(), extractTestTypeFromIntent())
        ).get(GraphViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        setSupportActionBar(graph_toolbar)
//        graph_fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toolbarColor = viewModel.getRandomColor()
        graph_toolbar.setBackgroundColor(toolbarColor)
        graph_toolbar_layout.setBackgroundColor(toolbarColor)

        viewModel.bloodTestItems.observe(this, Observer { items ->
            test_name_text.text = items.toString()
            drawGraph(items)
        })

        test_name_text.text = viewModel.bloodTestItems.value.toString()

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
            graph_toolbar_layout.title = getString(
                R.string.patient_title_name_template, item.firstName, item.secondName, item.surname)
        }else{
            graph_toolbar_layout.title = getString(
                R.string.patient_title_name_short_template, item.firstName, item.surname)
        }
    }

    private fun drawGraph(data: List<BloodTestItem>){
        val testGraph = test_graph
        val description = Description()
        description.text = "values"
        testGraph.description = description
        val dataSet = LineDataSet(getDataSet(data),
            BloodTestItem.getAbbreviation(viewModel.testName))
        dataSet.color = Color.GREEN
        dataSet.valueTextColor = Color.BLACK
        val lineData = LineData(dataSet)
        testGraph.data = lineData
        testGraph.invalidate()
    }

    private fun getDataSet(data: List<BloodTestItem>): List<Entry>{
        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, data[0].hemoglobin!!))
        entries.add(Entry(1f, data[1].hemoglobin!!))
        entries.add(Entry(2f, data[2].hemoglobin!!))
        entries.add(Entry(3f, data[3].hemoglobin!!))
        entries.add(Entry(4f, data[4].hemoglobin!!))
        return entries
    }

    //---===data extraction from intent===---
    private fun extractPatientItemFromIntent(): PatientItem? {
        return intent.getSerializableExtra("patientItem") as? PatientItem
    }

    private fun extractTestTypeFromIntent(): String {
        return intent.getStringExtra("testType")
    }
}
