package com.hendalqett.wifidetection.wifilist

import android.net.wifi.ScanResult
import android.text.TextUtils
import com.hend.airlines.ui.base.BasePresenter
import com.hendalqett.wifidetection.data.WifiNetwork

/**
 * Created by hend on 10/15/18.
 */
class WifiListPresenter(mView: WifiListContract.View) : BasePresenter<WifiListContract.View>(mView), WifiListContract.Presenter {

    lateinit var wifiNetworks: MutableList<WifiNetwork>

    override fun getListOFAllWifi(scanResults: List<ScanResult>?) {
        wifiNetworks = ArrayList()
        scanResults?.forEach { wifiNetwork ->

            val network = WifiNetwork(wifiNetwork.SSID, wifiNetwork.level)
            if (!TextUtils.isEmpty(network.name)) {
                wifiNetworks.add(network)
            }

        }

        wifiNetworks.sortByDescending { wifiNetwork -> wifiNetwork.level }

        getListOf3TopStrongWifi()
        getListOf3TopWeakWifi()
    }

    private fun getListOf3TopStrongWifi() {
        mView.get()?.onCloseByUpdate(wifiNetworks.take(3))
    }

    private fun getListOf3TopWeakWifi() {
        mView.get()?.onFarAwayUpdate(wifiNetworks.takeLast(wifiNetworks.size - 3).take(3), true)
    }

    override fun getListOf6TopWeakWifi() {
        mView.get()?.onFarAwayUpdate(wifiNetworks.takeLast(wifiNetworks.size - 3).take(6), false)
    }

}
