package com.hendalqett.wifidetection.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by hend on 10/13/18.
 */

@Parcelize
data class WifiNetwork(var name: String, var level: Int): Parcelable