package com.mjkrajsman.bloodtesttracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mjkrajsman.bloodtesttracker.model.Patient
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.model.RandomPatientGenerator
import com.mjkrajsman.bloodtesttracker.model.db.AppDatabase

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientListViewModel(application: Application): AndroidViewModel(application), ColorInterface {
    //TODO: LiveData? Dummy data should be replaced by permanent data set in the future.
    //TODO: Data for the View should be stored here
    //private val patients: LiveData<List<Patient>>? = null

    //private val aDb: AppDatabase? = null


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