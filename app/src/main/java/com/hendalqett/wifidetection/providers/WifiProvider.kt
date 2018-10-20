package com.hendalqett.wifidetection.providers

import com.hendalqett.wifidetection.data.model.WifiNetwork

/**
 * Created by hend on 10/19/18.
 */
interface WifiProvider {
    fun start()
    fun stop()

    interface Callback {
        fun onCloseByUpdate(items: List<WifiNetwork>)
        fun onFarAwayUpdate(items: List<WifiNetwork>)
    }
}