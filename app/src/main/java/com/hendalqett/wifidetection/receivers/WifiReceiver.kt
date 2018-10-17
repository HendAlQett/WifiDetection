package com.hendalqett.wifidetection.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager

/**
 * Created by hend on 10/15/18.
 */
class WifiReceiver(context: Context) : BroadcastReceiver() {

    private val context = context.applicationContext
    private var wifiReceiverListener = context as WifiReceiverListener
    private var wifiManager: WifiManager

    init {
        wifiManager = this@WifiReceiver.context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    fun startScan() {
        context.registerReceiver(this, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
        wifiManager.startScan()
    }

    override fun onReceive(context: Context, intent: Intent) {
        wifiReceiverListener.onWifiScannedResults(wifiManager.scanResults)
    }

    interface WifiReceiverListener {
        fun onWifiScannedResults(results: List<ScanResult>)
    }
}