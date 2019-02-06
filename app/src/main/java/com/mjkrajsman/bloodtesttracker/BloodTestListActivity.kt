package com.mjkrajsman.bloodtesttracker

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mjkrajsman.bloodtesttracker.model.BloodTestItem
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.recycler.BloodTestAdapter
import com.mjkrajsman.bloodtesttracker.viewmodel.BloodTestListViewModel
import com.mjkrajsman.bloodtesttracker.viewmodel.BloodTestListViewModelFactory
import kotlinx.android.synthetic.main.activity_blood_test_list.*

/**
 * Created by: Maciej Janusz Krajsman
 */
class BloodTestListActivity : AppCompatActivity() {
    private val viewModel: BloodTestListViewModel by lazy { ViewModelProviders.of(this,
        BloodTestListViewModelFactory(this.application, extractPatientItemFromIntent())
    ).get(BloodTestListViewModel::class.java) }

    //---===RecyclerView===---
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val viewAdapter by lazy { BloodTestAdapter(this::showBloodTestActivity, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_test_list)
        setSupportActionBar(blood_test_list_toolbar)
        blood_test_list_fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toolbarColor = viewModel.getRandomColor()
        blood_test_list_toolbar.setBackgroundColor(toolbarColor)
        blood_test_list_toolbar_layout.setBackgroundColor(toolbarColor)

        //---===RecyclerView===---
        viewManager = LinearLayoutManager(this)
        recyclerView = findViewById<RecyclerView>(R.id.blood_test_list_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.bloodTests.observe(this, Observer { items ->
            items?.let { viewAdapter.setItems(it) }
        })
        updateTitle(viewModel.patientItem!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun updateTitle(item: PatientItem) {
        if(item.secondName!=null) {
            blood_test_list_toolbar_layout.title = getString(
                R.string.patient_title_name_template, item.firstName, item.secondName, item.surname)
        }else{
            blood_test_list_toolbar_layout.title = getString(
                R.string.patient_title_name_short_template, item.firstName, item.surname)
        }

    }

    //---===data extraction from intent===---
    private fun extractPatientItemFromIntent(): PatientItem? {
        return intent.getSerializableExtra("patientItem") as? PatientItem
    }

    //---===data input to intent===---
    private fun showBloodTestActivity(bloodTestItem: BloodTestItem) {
        val intent = Intent(this, BloodTestActivity::class.java)
            .putExtra("bloodTestId", bloodTestItem.id)
        startActivity(intent)
    }

}
