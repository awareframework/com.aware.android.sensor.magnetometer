# AWARE Magnetometer

[![jitpack-badge](https://jitpack.io/v/awareframework/com.aware.android.sensor.magnetometer.svg)](https://jitpack.io/#awareframework/com.aware.android.sensor.magnetometer)

The magnetometer measures the geomagnetic field strength around the device. It lets you monitor changes in the Earth’s magnetic field. This sensor provides raw field strength data (in μT) for each of the axis.

![Sensor axes](http://www.awareframework.com/wp-content/uploads/2015/01/axis_device.png)

The coordinate-system is defined relative to the screen of the phone in its default orientation (facing the user). The axis are not swapped when the device’s screen orientation changes. The X axis is horizontal and points to the right, the Y axis is vertical and points up and the Z axis points towards the outside of the front face of the screen. In this system, coordinates behind the screen have negative Z axis. Also, the natural orientation of a device is not always portrait, as the natural orientation for many tablet devices is landscape. For more information, check the official [Android’s Sensor Coordinate System][3] documentation.

## Public functions

### MagnetometerSensor

+ `start(context: Context, config: MagnetometerSensor.Config?)`: Starts the magnetometer sensor with the optional configuration.
+ `stop(context: Context)`: Stops the service.
+ `currentInterval`: Data collection rate per second. (e.g. 5 samples per second)

### MagnetometerSensor.Config

Class to hold the configuration of the sensor.

#### Fields

+ `sensorObserver: MagnetometerSensor.Observer`: Callback for live data updates.
+ `interval: Int`: Data samples to collect per second. (default = 5)
+ `period: Float`: Period to save data in minutes. (default = 1)
+ `threshold: Double`: If set, do not record consecutive points if change in value is less than the set value.
+ `enabled: Boolean` Sensor is enabled or not. (default = false)
+ `debug: Boolean` enable/disable logging to `Logcat`. (default = false)
+ `label: String` Label for the data. (default = "")
+ `deviceId: String` Id of the device that will be associated with the events and the sensor. (default = "")
+ `dbEncryptionKey` Encryption key for the database. (default =String? = null)
+ `dbType: Engine` Which db engine to use for saving data. (default = `Engine.DatabaseType.NONE`)
+ `dbPath: String` Path of the database. (default = "aware_wifi")
+ `dbHost: String` Host for syncing the database. (Defult = `null`)

## Broadcasts

### Fired Broadcasts

+ `MagnetometerSensor.ACTION_AWARE_MAGNETOMETER` fired when magnetometer saved data to db after the period ends.

### Received Broadcasts

+ `MagnetometerSensor.ACTION_AWARE_MAGNETOMETER_START`: received broadcast to start the sensor.
+ `MagnetometerSensor.ACTION_AWARE_MAGNETOMETER_STOP`: received broadcast to stop the sensor.
+ `MagnetometerSensor.ACTION_AWARE_MAGNETOMETER_SYNC`: received broadcast to send sync attempt to the host.
+ `MagnetometerSensor.ACTION_AWARE_MAGNETOMETER_SET_LABEL`: received broadcast to set the data label. Label is expected in the `MagnetometerSensor.EXTRA_LABEL` field of the intent extras.

## Data Representations

### Magnetometer Sensor

Contains the hardware sensor capabilities in the mobile device.

| Field      | Type   | Description                                                     |
| ---------- | ------ | --------------------------------------------------------------- |
| maxRange   | Float  | Maximum sensor value possible                                   |
| minDelay   | Float  | Minimum sampling delay in microseconds                          |
| name       | String | Sensor’s name                                                  |
| power      | Float  | Sensor’s power drain in mA                                     |
| resolution | Float  | Sensor’s resolution in sensor’s units                         |
| type       | String | Sensor’s type                                                  |
| vendor     | String | Sensor’s vendor                                                |
| version    | String | Sensor’s version                                               |
| deviceId   | String | AWARE device UUID                                               |
| label      | String | Customizable label. Useful for data calibration or traceability |
| timestamp  | Long   | unixtime milliseconds since 1970                                |
| timezone   | Int    | [Raw timezone offset][1] of the device                          |
| os         | String | Operating system of the device (ex. android)                    |

### Magnetometer Data

Contains the raw sensor data.

| Field     | Type   | Description                                                     |
| --------- | ------ | --------------------------------------------------------------- |
| x         | Float  | the geomagnetic field strength along the x axis, in μT          |
| y         | Float  | the geomagnetic field strength along the y axis, in μT          |
| z         | Float  | the geomagnetic field strength along the z axis, in μT          |
| accuracy  | Int    | Sensor’s accuracy level (see [SensorManager][2])               |
| label     | String | Customizable label. Useful for data calibration or traceability |
| deviceId  | String | AWARE device UUID                                               |
| label     | String | Customizable label. Useful for data calibration or traceability |
| timestamp | Long   | unixtime milliseconds since 1970                                |
| timezone  | Int    | [Raw timezone offset][1] of the device                          |
| os        | String | Operating system of the device (ex. android)                    |

## Example usage

```kotlin
// To start the service.
MagnetometerSensor.start(appContext, MagnetometerSensor.Config().apply {
    sensorObserver = object : MagnetometerSensor.Observer {
        override fun onDataChanged(data: MagnetometerData) {
            // your code here...
        }
    }
    dbType = Engine.DatabaseType.ROOM
    debug = true
    // more configuration...
})

// To stop the service
MagnetometerSensor.stop(appContext)
```

## License

Copyright (c) 2018 AWARE Mobile Context Instrumentation Middleware/Framework (http://www.awareframework.com)

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

[1]: https://developer.android.com/reference/java/util/TimeZone#getRawOffset()
[2]: http://developer.android.com/reference/android/hardware/SensorManager.html
[3]: http://developer.android.com/guide/topics/sensors/sensors_overview.html#sensors-coords