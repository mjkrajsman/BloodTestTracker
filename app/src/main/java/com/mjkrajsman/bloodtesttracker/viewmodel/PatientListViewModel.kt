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
import com.mjkrajsman.bloodtesttracker.model.db.PatientDAO
import com.mjkrajsman.bloodtesttracker.repo.PatientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * Created by: Maciej Janusz Krajsman
 */
class PatientListViewModel(application: Application): AndroidViewModel(application), ColorInterface {
    //TODO: Dummy data should be replaced by permanent data set in the future.
    private val repository: PatientRepository
    val patients: LiveData<List<Patient>>
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val patientDAO = AppDatabase.getDatabase(application, scope).patientDAO()
        repository = PatientRepository(patientDAO)
        patients = repository.listLiveData
    }

    fun insert(patient: Patient) = scope.launch(Dispatchers.IO) {
        repository.insert(patient)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

}