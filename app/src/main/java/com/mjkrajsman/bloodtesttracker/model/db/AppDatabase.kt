package com.mjkrajsman.bloodtesttracker.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mjkrajsman.bloodtesttracker.model.BloodTest
import com.mjkrajsman.bloodtesttracker.model.Patient
import com.mjkrajsman.bloodtesttracker.model.RandomBloodTestGenerator
import com.mjkrajsman.bloodtesttracker.model.RandomPatientGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by: Maciej Janusz Krajsman
 */
@Database(entities = [Patient::class, BloodTest::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){
    abstract fun patientDAO(): PatientDAO
    abstract fun bloodTestDAO(): BloodTestDAO

    companion object {
        @Volatile
        private var instance:AppDatabase?=null

        fun getDatabase(context: Context, scope: CoroutineScope
        ): AppDatabase {
            return instance ?: synchronized(this) {
                // Create database here
                val inst = Room.databaseBuilder(context,AppDatabase::class.java, "BloodTestTrackerDB")
                    .addCallback(AppDatabaseCallback(scope)).build()
                    instance = inst
                inst
            }
        }
    }

    fun destroyInstance(){
        instance = null
    }

    private class AppDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        fun populateDatabase(patientDAO: PatientDAO, bloodTestDAO: BloodTestDAO) {
            patientDAO.deleteAllPatients()
            for(patientId in 1..10) {
                val patient = RandomPatientGenerator.generateDummyDbDataRow(patientId)
                patientDAO.insertPatient(patient)
                    for(bloodTestId in 1..5) {
                        val bloodTest = RandomBloodTestGenerator.generateDummyDbDataRow(
                            (patientId*10)+bloodTestId, patientId)
                        bloodTestDAO.insertBloodTest(bloodTest)
                    }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            instance?.let { database -> scope.launch(Dispatchers.IO) {
                    //TODO: remove random data later
                    populateDatabase(database.patientDAO(),database.bloodTestDAO())
                }
            }
        }
    }




}
