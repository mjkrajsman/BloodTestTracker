package com.mjkrajsman.bloodtesttracker.model

import org.joda.time.DateTime
import java.util.*

/**
 * Created by: Maciej Janusz Krajsman
 */
object RandomPatientGenerator {

    //generates random data to fill RecyclerView
    fun generateDummyData(): MutableList<PatientItem> {
        val data: MutableList<PatientItem> = mutableListOf()
        for(i in 1..100){
            val name2 = if (i % 2 == 0) null else "Name2"
            val patient = Patient(i,"Name1",name2,
                "Surname (" + i.toString() + ")", DateTime.now().minusMonths(i), Random().nextBoolean())
            data.add(PatientItem(patient))
        }
        return data
    }
}
