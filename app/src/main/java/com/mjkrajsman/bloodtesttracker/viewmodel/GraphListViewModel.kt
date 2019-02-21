package com.mjkrajsman.bloodtesttracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mjkrajsman.bloodtesttracker.model.BloodTestItem
import com.mjkrajsman.bloodtesttracker.model.PatientItem
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

/**
 * Created by: Maciej Janusz Krajsman
 */
class GraphListViewModel(application: Application, item: PatientItem?): AndroidViewModel(application), ColorInterface  {
    val patientItem: PatientItem? = item
    val tests: MutableLiveData<MutableList<String>>

    init {
        val testList: MutableList<String> = mutableListOf()
        val props = BloodTestItem::class.memberProperties//.toString()
        for (prop in props) {
            prop.isAccessible = true  // makes non-public properties accessible
            //TODO: avoid hardcoding?
            if(prop.name!="id" && prop.name!="patientId" && prop.name!="testDate") {
                testList.add(prop.name)
            }
        }
        tests = MutableLiveData(testList)
        for(test in testList){
            println(test)
            println(BloodTestItem.getAbbreviation(test))
        }
    }

    //TODO: number of tests with attribute
}