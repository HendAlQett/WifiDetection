package com.hendalqett.wifidetection.wifilist

import android.content.Context
import com.hend.airlines.ui.base.BasePresenter
import com.hendalqett.wifidetection.data.model.WifiNetwork
import com.hendalqett.wifidetection.providers.FakeWiFiProvider
import com.hendalqett.wifidetection.providers.RealWifiProvider
import com.hendalqett.wifidetection.providers.WifiProvider
import com.hendalqett.wifidetection.utils.FilterNetworksHandler

/**
 * Created by hend on 10/15/18.
 */
class WifiListPresenter(mView: WifiListContract.View) : BasePresenter<WifiListContract.View>(mView), WifiListContract.Presenter, WifiProvider.Callback {


    private val fakeWifiNetwork: MutableList<WifiNetwork> = ArrayList()
    private val realWifiNetwork: MutableList<WifiNetwork> = ArrayList()
    private lateinit var realWifiProvider: WifiProvider
    private lateinit var fakeWifiProvider: WifiProvider

    override fun onCloseByUpdate(items: List<WifiNetwork>) {
        realWifiNetwork.clear()
        realWifiNetwork.addAll(items)
        realWifiNetwork.sortByDescending { wifiNetwork -> wifiNetwork.level }
        mView.get()?.onCloseByUpdate(getListOfTopWifi(realWifiNetwork, 3))
    }

    override fun onFarAwayUpdate(items: List<WifiNetwork>) {
        fakeWifiNetwork.clear()
        fakeWifiNetwork.addAll(items)
        fakeWifiNetwork.sortByDescending { wifiNetwork -> wifiNetwork.level }
        mView.get()?.onFarAwayUpdate(getListOfTopWifi(fakeWifiNetwork, 3), true)
    }


    override fun startWifi() {
        realWifiProvider = RealWifiProvider(this, mView.get() as Context)
        fakeWifiProvider = FakeWiFiProvider(this)
        realWifiProvider.start()
        fakeWifiProvider.start()
    }

    override fun stopWifi() {
        realWifiProvider.stop()
        fakeWifiProvider.stop()
    }

    override fun getListOf6TopWeakWifi(){
        val networks =  getListOfTopWifi(fakeWifiNetwork, 6)
        mView.get()?.onFarAwayUpdate(networks, false)
    }

    private fun getListOfTopWifi(wifiNetworks: MutableList<WifiNetwork>, count: Int): List<WifiNetwork> {
        return FilterNetworksHandler.filterTop(count, wifiNetworks)

    }


}
