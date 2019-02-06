package com.mjkrajsman.bloodtesttracker.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by: Maciej Janusz Krajsman
 */
class BloodTestViewModelFactory(private val mApplication: Application, private val mParam: Int) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BloodTestViewModel(mApplication, mParam) as T
    }
}