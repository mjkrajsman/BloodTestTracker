package com.mjkrajsman.bloodtesttracker.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mjkrajsman.bloodtesttracker.model.Patient

/**
 * Created by: Maciej Janusz Krajsman
 */
@Dao
interface PatientDAO {
    @get:Query("SELECT * FROM patients")
    val allPatients: LiveData<List<Patient>>

    @Query("SELECT * FROM patients WHERE id=:patientId")
    fun getPatientById(patientId:Int): LiveData<Patient>

    @Insert
    fun insertPatient(vararg patients: Patient)

    @Update
    fun updatePatient(vararg patients: Patient)

    @Delete
    fun deletePatient(patient: Patient)

    @Query("DELETE FROM patients")
    fun deleteAllPatients()
}