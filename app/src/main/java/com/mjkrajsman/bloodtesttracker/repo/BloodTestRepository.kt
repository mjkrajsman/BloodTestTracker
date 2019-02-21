package com.mjkrajsman.bloodtesttracker.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.mjkrajsman.bloodtesttracker.model.BloodTest
import com.mjkrajsman.bloodtesttracker.model.db.BloodTestDAO

/**
 * Created by: Maciej Janusz Krajsman
 */
//TODO: make repository template?
class BloodTestRepository(private val bloodTestDAO: BloodTestDAO) {
    val listLiveData: LiveData<List<BloodTest>> = bloodTestDAO.allBloodTests

    @WorkerThread
    suspend fun insert(bloodTest: BloodTest) {
        bloodTestDAO.insertBloodTest()
    }

    @WorkerThread
    suspend fun getBloodTestById(id: Int): LiveData<BloodTest> {
        return bloodTestDAO.getBloodTestsById(id)
    }

    @WorkerThread
    suspend fun getBloodTestsByPatientId(patientId: Int): LiveData<List<BloodTest>>{
        return bloodTestDAO.getBloodTestsByPatientId(patientId)
    }

    @WorkerThread
    suspend fun getPatientByBloodTestId(bloodTestId: Int): Int {
        return bloodTestDAO.getPatientByBloodTestId(bloodTestId)
    }

    @WorkerThread
    suspend fun getBloodTestsByPatientIdAndTestName(patientId: Int, testName: String): LiveData<List<BloodTest>>{
        return bloodTestDAO.getBloodTestsByPatientIdAndTestName(patientId, testName)
    }
}