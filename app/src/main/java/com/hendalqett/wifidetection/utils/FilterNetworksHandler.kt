package com.hendalqett.wifidetection.utils

import com.hendalqett.wifidetection.data.model.WifiNetwork

/**
 * Created by hend on 10/16/18.
 */
class FilterNetworksHandler {
    companion object {
        fun filterTop(count: Int = 3, networks: MutableList<WifiNetwork>): List<WifiNetwork> {
            return networks.take(count)
        }

        fun filterLast(topCount: Int = 3, lastCount: Int, networks: MutableList<WifiNetwork>): List<WifiNetwork> {
            return networks.takeLast(networks.size - topCount).take(lastCount)
        }
    }
}
