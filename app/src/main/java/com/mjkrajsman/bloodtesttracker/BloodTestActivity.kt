package com.mjkrajsman.bloodtesttracker

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mjkrajsman.bloodtesttracker.model.BloodTestItem
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.viewmodel.BloodTestViewModel
import com.mjkrajsman.bloodtesttracker.viewmodel.BloodTestViewModelFactory
import kotlinx.android.synthetic.main.activity_blood_test.*
import kotlinx.android.synthetic.main.content_blood_test.*

/**
 * Created by: Maciej Janusz Krajsman
 */
class BloodTestActivity : AppCompatActivity() {
    private val viewModel: BloodTestViewModel by lazy { ViewModelProviders.of(this,
        BloodTestViewModelFactory(this.application, extractBloodTestIdFromIntent())
    ).get(BloodTestViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_test)
        setSupportActionBar(blood_test_toolbar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val toolbarColor = viewModel.getRandomColor()
        blood_test_toolbar.setBackgroundColor(toolbarColor)
        blood_test_toolbar_layout.setBackgroundColor(toolbarColor)
    }

    override fun onStart() {
        super.onStart()
        viewModel.bloodTest.observe(this, Observer { items ->
            items?.let { updateUIBloodTestData(BloodTestItem(viewModel.bloodTest.value!!)) }
        })
        viewModel.patient.observe(this, Observer { items ->
            items?.let { updateTitle(PatientItem(viewModel.patient.value!!)) }
        })
    }

    private fun updateTitle(item: PatientItem) {
        if(item.secondName!=null) {
            blood_test_toolbar_layout.title = getString(
                R.string.patient_title_name_template, item.firstName, item.secondName, item.surname)
        }else{
            blood_test_toolbar_layout.title = getString(
                R.string.patient_title_name_short_template, item.firstName, item.surname)
        }
    }

    private fun updateUIBloodTestData(item: BloodTestItem){
        test_date_text.text = getString(R.string.date_template, item.testDate.toLocalDate().toString())
        red_blood_cells_text.text = getString(R.string.red_blood_cells_template, item.redBloodCells)
        white_blood_cells_text.text = getString(R.string.white_blood_cells_template, item.whiteBloodCells)
        platelets_text.text = getString(R.string.platelets_template, item.platelets)
        hemoglobin_text.text = getString(R.string.hemoglobin_template, item.hemoglobin)
        hematocrit_text.text = getString(R.string.hematocrit_template, item.hematocrit)
        mean_corpuscular_volume_text.text = getString(R.string.mean_corpuscular_volume_template, item.meanCorpuscularVolume)
        mean_corpuscular_hemoglobin_text.text = getString(R.string.mean_corpuscular_hemoglobin_template, item.meanCorpuscularHemoglobin)
        mean_corpuscular_hemoglobin_concentration_text.text = getString(R.string.mean_corpuscular_hemoglobin_concentration_template, item.meanCorpuscularHemoglobinConcentration)
        red_cell_distribution_width_text.text = getString(R.string.red_cell_distribution_width_template, item.redCellDistributionWidth)
        mean_platelet_volume_text.text = getString(R.string.mean_platelet_volume_template, item.meanPlateletVolume)
        neutrophil_count_text.text = getString(R.string.neutrophil_count_template, item.neutrophilCount)
        basophil_count_text.text = getString(R.string.basophil_count_template, item.basophilCount)
        eosinophil_count_text.text = getString(R.string.eosinophil_count_template, item.eosinophilCount)
        lymphocyte_count_text.text = getString(R.string.lymphocyte_count_template, item.lymphocyteCount)
        monocyte_count_text.text = getString(R.string.monocyte_count_template, item.monocyteCount)
        neutrophil_percent_text.text = getString(R.string.neutrophil_percent_template, item.neutrophilPercent)
        basophil_percent_text.text = getString(R.string.basophil_percent_template, item.basophilPercent)
        eosinophil_percent_text.text = getString(R.string.eosinophil_percent_template, item.eosinophilPercent)
        lymphocyte_percent_text.text = getString(R.string.lymphocyte_percent_template, item.lymphocytePercent)
        monocyte_percent_text.text = getString(R.string.monocyte_percent_template, item.monocytePercent)
    }

    //---===data extraction from intent===---
    private fun extractBloodTestItemFromIntent(): BloodTestItem? {
        return intent.getSerializableExtra("bloodTestItem") as? BloodTestItem
    }

    private fun extractBloodTestIdFromIntent(): Int {
        return intent.getIntExtra("bloodTestId", 0)
    }
}
