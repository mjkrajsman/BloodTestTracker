package com.mjkrajsman.bloodtesttracker

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.viewmodel.PatientViewModel
import com.mjkrajsman.bloodtesttracker.viewmodel.PatientViewModelFactory
import kotlinx.android.synthetic.main.activity_patient.*
import kotlinx.android.synthetic.main.content_patient.*

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientActivity : AppCompatActivity() {
    private val viewModel: PatientViewModel by lazy { ViewModelProviders.of(this,
        PatientViewModelFactory(this.application, extractPatientItemFromIntent())
    ).get(PatientViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)
        setSupportActionBar(patient_toolbar)

        patient_fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toolbarColor = viewModel.getRandomColor()
        patient_toolbar.setBackgroundColor(toolbarColor)
        patient_toolbar_layout.setBackgroundColor(toolbarColor)

        button_blood_tests.setOnClickListener(this::showBloodTestListActivity)
        button_graphs.setOnClickListener(this::showGraphListActivity)

    }

    override fun onStart() {
        super.onStart()
        updateUI(viewModel.patientItem!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun updateUI(item: PatientItem){
        if(item.secondName!=null) {
            patient_toolbar_layout.title = getString(
                R.string.patient_title_name_template, item.firstName, item.secondName, item.surname)
            name_text.text = getString(R.string.patient_names_template, item.firstName, item.secondName)
        }else{
            patient_toolbar_layout.title = getString(
                R.string.patient_title_name_short_template, item.firstName, item.surname)
            name_text.text = getString(R.string.patient_name_template, item.firstName)
        }

        surname_text.text = getString(R.string.patient_surname_template, item.surname)
        birth_date_text.text = getString(R.string.patient_birth_date_template, item.birthDate.toLocalDate().toString())
        age_text.text = resources.getQuantityString(R.plurals.patient_age_template, item.age, item.age)
        if (item.isMale) {
            sex_image.setImageDrawable(getDrawable(R.drawable.ic_male_3))
        } else {
            sex_image.setImageDrawable(getDrawable(R.drawable.ic_female_3))
        }
    }

    //---===data extraction from intent===---
    private fun extractPatientItemFromIntent(): PatientItem? {
        return intent.getSerializableExtra("patientItem") as? PatientItem
    }

    //---===data input to intent===---
    private fun showBloodTestListActivity(view: View) {
        val intent = Intent(this, BloodTestListActivity::class.java)
            //TODO: id or item
            //.putExtra("id", viewModel.patientItem!!.id)
            .putExtra("patientItem", viewModel.patientItem)
        startActivity(intent)
    }

    private fun showGraphListActivity(view: View) {
        val intent = Intent(this, GraphListActivity::class.java)
            //TODO: id or item
            //.putExtra("id", viewModel.patientItem!!.id)
            .putExtra("patientItem", viewModel.patientItem)
        startActivity(intent)
    }
}
