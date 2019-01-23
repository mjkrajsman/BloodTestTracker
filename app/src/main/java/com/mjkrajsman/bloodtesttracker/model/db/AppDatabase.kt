package com.mjkrajsman.bloodtesttracker.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mjkrajsman.bloodtesttracker.model.Patient

/**
 * Created by: Maciej Janusz Krajsman
 */

@Database(entities = [Patient::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    private var instance:AppDatabase?=null
    abstract fun patientDAO(): PatientDAO
    //abstract fun patientDAO(): BloodTestDAO

    fun getFileDatabase(context: Context):AppDatabase{
        if(instance == null)
            instance = Room.databaseBuilder(context,AppDatabase::class.java, "BloodTestTrackerDB").build()
        return instance!!
    }
    fun getInMemoryDatabase(context: Context):AppDatabase{
        if(instance == null)
            instance = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).build()
        return instance!!
    }
    fun destroyInstance(){
        instance = null
    }
}
