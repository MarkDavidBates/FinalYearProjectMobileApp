package org.wit.wishlistandroid.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// TODO: new model = title, id, min max temperature, min max humidity, min max moisture

@Parcelize
data class NodeModel(var id: Long = 0,
                     var title: String = "",
                     var penID: String = "",
                     var temperature: Int = 0,
                     var humidity: Int = 0,
                     var moisture: Int = 0) : Parcelable
