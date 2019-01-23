package com.mjkrajsman.bloodtesttracker.model

import org.joda.time.DateTime

/**
 * Created by: Maciej Janusz Krajsman
 */

interface DateTimeMillisConverterInterface {

    fun millisToDateTime(m: Long): DateTime {
        return DateTime(m)
    }

    fun dateTimeToMillis(dt: DateTime): Long{
        return dt.millis
    }
}