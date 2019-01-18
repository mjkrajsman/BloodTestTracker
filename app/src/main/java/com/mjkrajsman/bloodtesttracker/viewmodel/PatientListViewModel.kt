package com.mjkrajsman.bloodtesttracker.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mjkrajsman.bloodtesttracker.ColorInterface
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.model.RandomPatientGenerator

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientListViewModel: ViewModel(), ColorInterface {
    //TODO: LiveData? Dummy data should be replaced by permanent data set in the future.
    //TODO: Data for the View should be stored here
    //private val patients: MutableLiveData<MutableList<PatientItem>> = MutableLiveData()

    //---===get data from Model (for View)===---

    //generates random data to fill RecyclerView
    fun getRandomPatientList(): MutableList<PatientItem>{
        return RandomPatientGenerator.generateDummyData() //from model
    }

    fun getPatientList(){
        //TODO: implement this //from model
    }

    //---===Model data management===---
    fun addPatient(){
        //TODO: implement this //to model
    }

}