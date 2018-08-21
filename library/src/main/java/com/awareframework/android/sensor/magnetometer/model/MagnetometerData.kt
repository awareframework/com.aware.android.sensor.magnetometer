package com.awareframework.android.sensor.magnetometer.model

import com.awareframework.android.core.model.AwareObject
import com.google.gson.Gson

/**
 * Contains the raw sensor data.
 *
 * @author  sercant
 * @date 20/08/2018
 */
data class MagnetometerData(
        var x: Float = 0f,
        var y: Float = 0f,
        var z: Float = 0f,
        var eventTimestamp: Long = 0L,
        var accuracy: Int = 0
) : AwareObject(jsonVersion = 1) {

    companion object {
        const val TABLE_NAME = "magnetometerData"
    }

    override fun toString(): String = Gson().toJson(this)
}