package com.mjkrajsman.bloodtesttracker.model

import org.joda.time.DateTime
import org.joda.time.Years

/**
 * Created by: Maciej Janusz Krajsman
 */
data class Patient(
    val index: Int,
    val firstName: String,
    val secondName: String?,
    val surname: String,
    val birthDate: DateTime,
    val sex: Boolean //0 = female, 1 = male //TODO: make enum od leave as boolean?
){
    fun getAge(): Int {
        return Years.yearsBetween(birthDate,DateTime.now()).years
    }
}