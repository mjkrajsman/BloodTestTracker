package com.mjkrajsman.bloodtesttracker.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.joda.time.DateTime
import org.joda.time.Years

/**
 * Created by: Maciej Janusz Krajsman
 */
@Entity(tableName = "patients")
data class Patient(
    @NonNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int,
    @NonNull @ColumnInfo(name = "first_name") var firstName:String,
    @ColumnInfo(name = "second_name") var secondName:String? = null,
    @NonNull @ColumnInfo(name = "surname") var surname:String,
    @NonNull @ColumnInfo(name = "birth_date") var birthDate: Long,
    @NonNull @ColumnInfo(name = "isMale") var isMale:Boolean
): DateTimeMillisConverterInterface {

    fun getAge(): Int {
        return Years.yearsBetween(DateTime(birthDate),DateTime.now()).years
    }

    //TODO: use in constructor?
    fun setBirthDateToLong(dt:DateTime){
        this.birthDate = dateTimeToMillis(dt)
    }

    fun getBirthDateFromLong():DateTime{
        return millisToDateTime(birthDate)
    }
}