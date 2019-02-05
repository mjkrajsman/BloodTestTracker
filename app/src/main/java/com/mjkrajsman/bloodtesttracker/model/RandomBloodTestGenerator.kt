package com.mjkrajsman.bloodtesttracker.model

import org.joda.time.DateTime
import kotlin.random.Random

/**
 * Created by: Maciej Janusz Krajsman
 */
object RandomBloodTestGenerator {
    private const val min = 5.0f
    private const val max = 15.0f

    private fun generateRandomFloat(min: Float, max: Float, nulls: Boolean): Float?{
        var res: Float? = min + Random.nextFloat() * (max - min)
        if (nulls){
            val rand = Random.nextFloat()
            if(rand>=0.75f) res = null
        }
        return res
    }

    fun generateDummyDbDataRow(i:Int, patientId: Int): BloodTest{
        return BloodTest(i,patientId, DateTime.now().minusMonths(i).millis,
            generateRandomFloat(min, max, false),
            generateRandomFloat(min, max, false),
            generateRandomFloat(min, max, false),
            generateRandomFloat(min, max, false),
            generateRandomFloat(min, max, false),
            generateRandomFloat(min, max, false),
            generateRandomFloat(min, max, false),
            generateRandomFloat(min, max, false),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true),
            generateRandomFloat(min, max, true)
        )
    }


}
