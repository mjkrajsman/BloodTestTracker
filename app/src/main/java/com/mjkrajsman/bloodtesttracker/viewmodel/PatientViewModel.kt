package com.mjkrajsman.bloodtesttracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mjkrajsman.bloodtesttracker.model.PatientItem

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientViewModel(application: Application, item: PatientItem?): AndroidViewModel(application), ColorInterface {
    val patientItem: PatientItem? = item
}