package com.mjkrajsman.bloodtesttracker

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.viewmodel.PatientViewModel
import kotlinx.android.synthetic.main.activity_patient.*
import kotlinx.android.synthetic.main.content_patient.*

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientActivity : AppCompatActivity() {
    private lateinit var viewModel: PatientViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)
        setSupportActionBar(patient_toolbar)

        patient_fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //get toolbar color from intent
        patient_toolbar.setBackgroundColor(extractColorFromIntent())
        patient_toolbar_layout.setBackgroundColor(extractColorFromIntent())

        //---===patientItem data===---
        //TODO: move data (patientItem) to viewModel
        val patientItem: PatientItem? = extractPatientItemFromIntent()
        if(patientItem?.secondName!=null) {
            patient_toolbar_layout.title = getString(
                R.string.patient_title_name_template, patientItem.firstName,
                patientItem.secondName, patientItem.surname)
            name_text.text = getString(
                R.string.patient_names_template, patientItem.firstName,
                patientItem.secondName)
        }else{
            patient_toolbar_layout.title = getString(
                R.string.patient_title_name_short_template, patientItem?.firstName,
                patientItem?.surname)
            name_text.text = getString(
                R.string.patient_name_template, patientItem?.firstName)
        }

        surname_text.text = getString(R.string.patient_surname_template, patientItem?.surname)
        birth_date_text.text = getString(R.string.patient_birth_date_template, patientItem?.birthDate?.toLocalDate().toString())

        if(patientItem!=null) {
            age_text.text = resources.getQuantityString(R.plurals.patient_age_template, patientItem.age, patientItem.age)
            if (patientItem.isMale) {
                sex_image.setImageDrawable(getDrawable(R.drawable.ic_male_3))
            } else {
                sex_image.setImageDrawable(getDrawable(R.drawable.ic_female_3))
            }
        }else{
            val ageNullText = getString(R.string.age) + " null"
            age_text.text = ageNullText
            //sex_image.setImageDrawable(getDrawable(R.drawable.notification_bg))
            resources.getIdentifier("android:drawable/ic_delete", null, null)
        }


    }

    //---===data extraction from intent===---
    private fun extractColorFromIntent(): Int {
        return intent.getIntExtra("color", Color.BLACK)
    }

    //TODO: store and pass data in another way? PatientItem should be accessible by ViewModel.
    private fun extractPatientItemFromIntent(): PatientItem? {
        return intent.getSerializableExtra("patientItem") as? PatientItem
    }


}
