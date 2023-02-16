package org.wit.wishlistandroid.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// TODO: new model = title, id, min max temperature, min max humidity, min max moisture

@Parcelize
data class NodeModel(var id: Long = 0,
                     var title: String = "",
                     var minTemp: Double = 0.0,
                     var maxTemp: Double = 0.0,
                     var minHumid: Double = 0.0,
                     var maxHumid: Double = 0.0,
                     var minMoisture: Double = 0.0,
                     var maxMoisture: Double = 0.0) : Parcelable
