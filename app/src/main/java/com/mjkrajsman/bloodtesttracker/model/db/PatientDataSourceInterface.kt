package com.mjkrajsman.bloodtesttracker.model.db

import androidx.lifecycle.LiveData
import com.mjkrajsman.bloodtesttracker.model.Patient
import io.reactivex.Flowable

/**
 * Created by: Maciej Janusz Krajsman
 */

interface PatientDataSourceInterface {
    //val allPatients:Flowable<List<Patient>>
    val allPatients:LiveData<List<Patient>>
    //fun getPatientById(patientId:Int):Flowable<Patient>
    fun getPatientById(patientId:Int):LiveData<Patient>
    fun insertPatient(vararg patients:Patient)
    fun updatePatient(vararg patients:Patient)
    fun deletePatient(patient:Patient)
    fun deleteAllPatients()
}