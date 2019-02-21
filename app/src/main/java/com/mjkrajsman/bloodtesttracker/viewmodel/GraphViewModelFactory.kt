package com.mjkrajsman.bloodtesttracker.viewmodel

import androidx.lifecycle.ViewModel
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.mjkrajsman.bloodtesttracker.model.PatientItem

/**
 * Created by: Maciej Janusz Krajsman
 */
class GraphViewModelFactory(private val mApplication: Application, private val mParam: PatientItem?, private val mParam2: String) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GraphViewModel(mApplication, mParam, mParam2) as T
    }
}
