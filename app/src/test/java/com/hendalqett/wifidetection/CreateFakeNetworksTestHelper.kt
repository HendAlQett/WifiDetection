package com.hendalqett.wifidetection

import com.hendalqett.wifidetection.data.model.WifiNetwork

/**
 * Created by hend on 10/16/18.
 */
object CreateFakeNetworksTestHelper {

    fun createWifiNetworks(): MutableList<WifiNetwork> {

        val networks: MutableList<WifiNetwork> = ArrayList()
        networks.add(WifiNetwork("Wifi 1", -20))
        networks.add(WifiNetwork("Wifi 2", -90))
        networks.add(WifiNetwork("Wifi 3", -100))
        networks.add(WifiNetwork("Wifi 4", -20))
        networks.add(WifiNetwork("Wifi 5", -40))
        networks.add(WifiNetwork("Wifi 6", -30))
        networks.add(WifiNetwork("Wifi 7", -20))
        networks.add(WifiNetwork("Wifi 8", -10))

        return networks
    }
}
