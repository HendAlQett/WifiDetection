package com.hendalqett.wifidetection.wifilist

import android.net.wifi.ScanResult
import android.text.TextUtils
import com.hend.airlines.ui.base.BasePresenter
import com.hendalqett.wifidetection.data.model.WifiNetwork
import com.hendalqett.wifidetection.utils.FilterNetworksHandler

/**
 * Created by hend on 10/15/18.
 */
class WifiListPresenter(mView: WifiListContract.View) : BasePresenter<WifiListContract.View>(mView), WifiListContract.Presenter{

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
        val networks: List<WifiNetwork> = FilterNetworksHandler.filterTop(3, wifiNetworks)
        mView.get()?.onCloseByUpdate(networks)
    }

    private fun getListOf3TopWeakWifi() {
        val networks: List<WifiNetwork> = FilterNetworksHandler.filterLast(3, 3, wifiNetworks)
        mView.get()?.onFarAwayUpdate(networks, true)
    }

    override fun getListOf6TopWeakWifi() {
        val networks: List<WifiNetwork> = FilterNetworksHandler.filterLast(3, 6, wifiNetworks)
        mView.get()?.onFarAwayUpdate(networks, false)
    }


}
