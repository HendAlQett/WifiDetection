package com.hendalqett.wifidetection.wifilist

import android.net.wifi.ScanResult
import android.support.v7.app.AppCompatActivity
import com.hend.airlines.ui.base.BaseContract
import com.hendalqett.wifidetection.data.model.WifiNetwork

/**
 * Created by hend on 10/15/18.
 */
class WifiListContract : BaseContract {
    interface View : BaseContract.View<AppCompatActivity> {
        fun onCloseByUpdate(items: List<WifiNetwork>)
        fun onFarAwayUpdate(items: List<WifiNetwork>, isShowButton: Boolean)
    }

    interface Presenter : BaseContract.Presenter {
        fun getListOFAllWifi(scanResults: List<ScanResult>?)

        fun getListOf6TopWeakWifi()

    }
}
