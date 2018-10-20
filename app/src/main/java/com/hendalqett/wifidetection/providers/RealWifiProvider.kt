package com.hendalqett.wifidetection.providers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.text.TextUtils
import com.hendalqett.wifidetection.data.model.WifiNetwork

/**
 * Created by hend on 10/15/18.
 */
class RealWifiProvider(private val callback: WifiProvider.Callback, context: Context) : BroadcastReceiver(), WifiProvider {

    private val context = context.applicationContext
    private var wifiManager: WifiManager

    init {
        wifiManager = this@RealWifiProvider.context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    override fun start() {
        context.registerReceiver(this, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        wifiManager.startScan()
    }

    override fun onReceive(context: Context, intent: Intent) {
        callback.onCloseByUpdate(generateRealWiFis(wifiManager.scanResults))
    }

    private fun generateRealWiFis(scanResults: List<ScanResult>?): List<WifiNetwork> {
        val wifiNetworks = mutableListOf<WifiNetwork>()
        scanResults?.forEach { wifiNetwork ->

            val network = WifiNetwork(wifiNetwork.SSID, wifiNetwork.level)
            if (!TextUtils.isEmpty(network.name)) {
                wifiNetworks.add(network)
            }

        }

        wifiNetworks.sortByDescending { wifiNetwork -> wifiNetwork.level }
        return wifiNetworks
    }

    override fun stop() {
        context.unregisterReceiver(this)
    }
}