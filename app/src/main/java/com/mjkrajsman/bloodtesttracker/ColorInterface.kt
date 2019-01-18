package com.mjkrajsman.bloodtesttracker

import android.graphics.Color
import java.util.*

/**
 * Created by: Maciej Janusz Krajsman
 */
interface ColorInterface {
    fun getRandomColor(): Int {
        return RandomColor.generate()
    }

    private object RandomColor {
        private val random = Random()

        fun generate(): Int = Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255))
    }

}