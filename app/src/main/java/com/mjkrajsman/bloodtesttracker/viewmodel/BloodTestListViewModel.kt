package com.mjkrajsman.bloodtesttracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.mjkrajsman.bloodtesttracker.model.BloodTest
import com.mjkrajsman.bloodtesttracker.model.BloodTestItem
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
    private val bloodTestRepository: BloodTestRepository
    val bloodTestItems: LiveData<List<BloodTestItem>>
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    init {
        val bloodTestDAO = AppDatabase.getDatabase(application, scope).bloodTestDAO()
        bloodTestRepository = BloodTestRepository(bloodTestDAO)
        bloodTestItems = getBloodTestItemsByPatientId(patientItem!!.id)
    }

    fun insert(bloodTest: BloodTest) = scope.launch(Dispatchers.IO) {
        bloodTestRepository.insert(bloodTest)
    }

    private fun getBloodTestItemsByPatientId(bloodTestId: Int): LiveData<List<BloodTestItem>> {
        return runBlocking {
            Transformations.map(bloodTestRepository.getBloodTestsByPatientId(bloodTestId)
            ) { data -> makeBloodTestItemsFromBloodTests(data) }
        }
    }

    private fun makeBloodTestItemsFromBloodTests(bloodTests: List<BloodTest>): List<BloodTestItem> {
        val res = ArrayList<BloodTestItem>()
        var bloodTestItem: BloodTestItem
        for (item in bloodTests) {
            bloodTestItem = BloodTestItem(item)
            res.add(bloodTestItem)
        }
        return res
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}