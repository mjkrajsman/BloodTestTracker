package com.mjkrajsman.bloodtesttracker.viewmodel

import androidx.lifecycle.ViewModel
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.mjkrajsman.bloodtesttracker.model.PatientItem

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientViewModelFactory(private val mApplication: Application, private val mParam: PatientItem?) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PatientViewModel(mApplication, mParam) as T
    }
}
