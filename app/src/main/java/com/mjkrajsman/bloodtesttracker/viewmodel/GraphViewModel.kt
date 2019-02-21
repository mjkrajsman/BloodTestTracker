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
class GraphViewModel(application: Application, item: PatientItem?, tName: String): AndroidViewModel(application), ColorInterface  {
    private val bloodTestRepository: BloodTestRepository
    lateinit var bloodTestItems: LiveData<List<BloodTestItem>>
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    val patientItem: PatientItem? = item
    val testName: String = tName

    init {
        val bloodTestDAO = AppDatabase.getDatabase(application, scope).bloodTestDAO()
        bloodTestRepository = BloodTestRepository(bloodTestDAO)
        runBlocking {
            withContext(Dispatchers.IO) {
                bloodTestItems = getBloodTestItemsByPatientId(patientItem!!.id)
            }
        }
    }

    private fun getBloodTestsByPatientIdAndTestName(bloodTestId: Int, testName: String): LiveData<List<BloodTest>> {
        return runBlocking {
            bloodTestRepository.getBloodTestsByPatientIdAndTestName(bloodTestId, testName)
        }
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