package com.mjkrajsman.bloodtesttracker.model

import java.io.Serializable
import org.joda.time.DateTime

/**
 * Created by: Maciej Janusz Krajsman
 */
data class PatientItem(
    val index: Int,
    val firstName: String,
    val secondName: String?,
    val surname: String,
    val birthDate: DateTime,
    val sex: Boolean,
    val age: Int
                ): Serializable {

    constructor(patient: Patient): this(
        patient.index,
        patient.firstName,
        patient.secondName,
        patient.surname,
        patient.birthDate,
        patient.sex,
        patient.getAge()
    )

}


