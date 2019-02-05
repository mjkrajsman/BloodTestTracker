package com.mjkrajsman.bloodtesttracker.model

import java.io.Serializable
import org.joda.time.DateTime

/**
 * Created by: Maciej Janusz Krajsman
 */
data class PatientItem(
    val id: Int,
    val firstName: String,
    val secondName: String?,
    val surname: String,
    val birthDate: DateTime,
    val isMale: Boolean,
    val age: Int
                ): Serializable {
    constructor(patient: Patient): this(
        id = patient.id,
        firstName = patient.firstName,
        secondName = patient.secondName,
        surname = patient.surname,
        birthDate = patient.getBirthDateFromLong(),
        isMale = patient.isMale,
        age = patient.getAge()
    )

}


