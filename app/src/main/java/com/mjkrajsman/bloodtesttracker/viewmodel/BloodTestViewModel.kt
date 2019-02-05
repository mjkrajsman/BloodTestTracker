package com.mjkrajsman.bloodtesttracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mjkrajsman.bloodtesttracker.model.BloodTest
import com.mjkrajsman.bloodtesttracker.model.Patient
import com.mjkrajsman.bloodtesttracker.model.db.AppDatabase
import com.mjkrajsman.bloodtesttracker.repo.BloodTestRepository
import com.mjkrajsman.bloodtesttracker.repo.PatientRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by: Maciej Janusz Krajsman
 */
class BloodTestViewModel(application: Application, id: Int): AndroidViewModel(application), ColorInterface  {
    private val bloodTestRepository: BloodTestRepository
    private val patientRepository: PatientRepository
    lateinit var bloodTest: LiveData<BloodTest>
    lateinit var patient: LiveData<Patient>
    var patientId: Int = 0
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val patientDAO = AppDatabase.getDatabase(application, scope).patientDAO()
        val bloodTestDAO = AppDatabase.getDatabase(application, scope).bloodTestDAO()
        patientRepository = PatientRepository(patientDAO)
        bloodTestRepository = BloodTestRepository(bloodTestDAO)
        runBlocking {
            withContext(Dispatchers.IO) {
                bloodTest = getBloodTestById(id)
                patientId = getPatientByBloodTestId(id)
                patient = getPatientById(patientId)
            }
        }

    }

    fun insert(bloodTest: BloodTest) = scope.launch(Dispatchers.IO) {
        bloodTestRepository.insert(bloodTest)
    }

    private fun getPatientById(patientId: Int): LiveData<Patient>{
        return runBlocking{
            patientRepository.getPatientById(patientId)
        }
    }

    private fun getBloodTestById(bloodTestId: Int): LiveData<BloodTest>{
        return runBlocking{
            bloodTestRepository.getBloodTestById(bloodTestId)
        }
    }

    private fun getPatientByBloodTestId(bloodTestId: Int): Int{
        return runBlocking{
            bloodTestRepository.getPatientByBloodTestId(bloodTestId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}