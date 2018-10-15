package com.hendalqett.wifidetection.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by hend on 10/13/18.
 */
data class WifiNetwork(var name: String, var level: Int = -300): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(level)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WifiNetwork> {
        override fun createFromParcel(parcel: Parcel): WifiNetwork {
            return WifiNetwork(parcel)
        }

        override fun newArray(size: Int): Array<WifiNetwork?> {
            return arrayOfNulls(size)
        }
    }
}
