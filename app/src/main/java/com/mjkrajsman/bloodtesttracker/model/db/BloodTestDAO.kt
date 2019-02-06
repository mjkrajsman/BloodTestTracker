package com.mjkrajsman.bloodtesttracker.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mjkrajsman.bloodtesttracker.model.BloodTest

/**
 * Created by: Maciej Janusz Krajsman
 */
@Dao
interface BloodTestDAO {
    @get:Query("SELECT * FROM blood_tests")
    val allBloodTests: LiveData<List<BloodTest>>

    @Query("SELECT * FROM blood_tests WHERE id=:bloodTestId")
    fun getBloodTestsById(bloodTestId: Int): LiveData<BloodTest>

    @Query("SELECT * FROM blood_tests WHERE patient_id=:patientId")
    fun getBloodTestsByPatientId(patientId: Int): LiveData<List<BloodTest>>

    @Query("SELECT patient_id FROM blood_tests WHERE id=:bloodTestId")
    fun getPatientByBloodTestId(bloodTestId: Int): Int

    @Insert
    fun insertBloodTest(vararg bloodTests: BloodTest)

    @Update
    fun updateBloodTest(vararg bloodTests: BloodTest)

    @Delete
    fun deleteBloodTest(bloodTests: BloodTest)

    @Query("DELETE FROM blood_tests")
    fun deleteAllBloodTests()
}