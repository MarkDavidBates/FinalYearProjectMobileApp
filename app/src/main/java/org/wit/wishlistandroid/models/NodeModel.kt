package org.wit.wishlistandroid.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// TODO: new model = title, id, min max temperature, min max humidity, min max moisture

@Parcelize
data class NodeModel(var id: Long = 0,
                     var title: String = "",
                     var minTemp: Int = 0,
                     var maxTemp: Int = 0,
                     var minHumid: Int = 0,
                     var maxHumid: Int = 0,
                     var minMoisture: Int = 0,
                     var maxMoisture: Int = 0) : Parcelable
