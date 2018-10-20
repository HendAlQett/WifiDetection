package com.hendalqett.wifidetection

import android.content.Context
import com.hendalqett.wifidetection.providers.FakeWiFiProvider
import com.hendalqett.wifidetection.providers.RealWifiProvider
import com.hendalqett.wifidetection.providers.WifiProvider
import com.hendalqett.wifidetection.wifilist.WifiListContract
import com.hendalqett.wifidetection.wifilist.WifiListPresenter
import org.koin.dsl.module.module

/**
 * Created by hend on 10/15/18.
 */


val appModule = module {

    factory { (view: WifiListContract.View) -> WifiListPresenter(view) }
    factory { (callback: WifiProvider.Callback,context: Context) -> RealWifiProvider(callback, context) }
    factory { (callback: WifiProvider.Callback) -> FakeWiFiProvider(callback) }

}


