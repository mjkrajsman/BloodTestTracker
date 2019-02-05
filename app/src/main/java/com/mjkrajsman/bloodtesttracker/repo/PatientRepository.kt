package com.mjkrajsman.bloodtesttracker.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.mjkrajsman.bloodtesttracker.model.Patient
import com.mjkrajsman.bloodtesttracker.model.db.PatientDAO

/**
 * Created by: Maciej Janusz Krajsman
 */
//TODO: make repository template?
class PatientRepository(private val patientDAO: PatientDAO) {
    val listLiveData: LiveData<List<Patient>> = patientDAO.allPatients

    @WorkerThread
    suspend fun insert(patient: Patient) {
        //return async {
            patientDAO.insertPatient(patient)
        //}.await()
    }

    @WorkerThread
    suspend fun getPatientById(id: Int): LiveData<Patient> {
        return patientDAO.getPatientById(id)
    }
}