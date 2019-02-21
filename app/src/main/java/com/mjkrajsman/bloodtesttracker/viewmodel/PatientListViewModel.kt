package com.mjkrajsman.bloodtesttracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mjkrajsman.bloodtesttracker.model.Patient
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import com.mjkrajsman.bloodtesttracker.model.db.AppDatabase
import com.mjkrajsman.bloodtesttracker.repo.PatientRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientListViewModel(application: Application): AndroidViewModel(application), ColorInterface {
    //TODO: Dummy data should be replaced by permanent data set in the future.
    private val patientRepository: PatientRepository
    val patientItems: LiveData<List<PatientItem>>
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val patientDAO = AppDatabase.getDatabase(application, scope).patientDAO()
        patientRepository = PatientRepository(patientDAO)
        patientItems = getPatientItemsByPatientId()//patientRepository.listLiveData
    }

    fun insert(patient: Patient) = scope.launch(Dispatchers.IO) {
        patientRepository.insert(patient)
    }

    private fun getPatientItemsByPatientId(): LiveData<List<PatientItem>> {
        return runBlocking {
            Transformations.map(patientRepository.listLiveData
            ) { data -> makePatientItemsFromPatients(data) }
        }
    }

    private fun makePatientItemsFromPatients(patients: List<Patient>): List<PatientItem> {
        val res = ArrayList<PatientItem>()
        var patientItem: PatientItem
        for (item in patients) {
            patientItem = PatientItem(item)
            res.add(patientItem)
        }
        return res
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}