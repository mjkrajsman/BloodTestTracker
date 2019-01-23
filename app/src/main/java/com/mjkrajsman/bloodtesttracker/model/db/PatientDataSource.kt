package com.mjkrajsman.bloodtesttracker.model.db

import androidx.lifecycle.LiveData
import com.mjkrajsman.bloodtesttracker.model.Patient
import io.reactivex.Flowable

/**
 * Created by: Maciej Janusz Krajsman
 */

class PatientDataSource(private val patientDAO: PatientDAO):PatientDataSourceInterface{
//    override val allPatients: Flowable<List<Patient>>
//        get() = patientDAO.allPatients
//
//    override fun getPatientById(patientId: Int): Flowable<Patient> {
//        return patientDAO.getPatientById(patientId)
//    }
    override val allPatients: LiveData<List<Patient>>
        get() = patientDAO.allPatients

    override fun getPatientById(patientId: Int): LiveData<Patient> {
        return patientDAO.getPatientById(patientId)
    }

    override fun insertPatient(vararg patients: Patient) {
        patientDAO.insertPatient(*patients)
    }

    override fun updatePatient(vararg patients: Patient) {
        patientDAO.updatePatient(*patients)
    }

    override fun deletePatient(patient: Patient) {
        patientDAO.deletePatient(patient)
    }

    override fun deleteAllPatients() {
        patientDAO.deleteAllPatients()
    }

}