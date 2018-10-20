package com.hendalqett.wifidetection

import com.hendalqett.wifidetection.wifilist.WifiListContract
import com.hendalqett.wifidetection.wifilist.WifiListPresenter
import org.koin.dsl.module.module

/**
 * Created by hend on 10/15/18.
 */


val appModule = module {

    factory { (view: WifiListContract.View) -> WifiListPresenter(view) }
}


