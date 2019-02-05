package com.mjkrajsman.bloodtesttracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mjkrajsman.bloodtesttracker.model.BloodTest
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.model.db.AppDatabase
import com.mjkrajsman.bloodtesttracker.repo.BloodTestRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by: Maciej Janusz Krajsman
 */
class BloodTestListViewModel(application: Application, item: PatientItem?): AndroidViewModel(application), ColorInterface  {
    val patientItem: PatientItem? = item
    private val repository: BloodTestRepository
    val bloodTests: LiveData<List<BloodTest>>
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val bloodTestDAO = AppDatabase.getDatabase(application, scope).bloodTestDAO()
        repository = BloodTestRepository(bloodTestDAO)
        bloodTests = getBloodTestsByPatientId(patientItem!!.id)
    }

    fun insert(bloodTest: BloodTest) = scope.launch(Dispatchers.IO) {
        repository.insert(bloodTest)
    }

    private fun getBloodTestsByPatientId(patientId: Int): LiveData<List<BloodTest>>{
        return runBlocking{
            repository.getBloodTestsByPatientId(patientId)
        }
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}