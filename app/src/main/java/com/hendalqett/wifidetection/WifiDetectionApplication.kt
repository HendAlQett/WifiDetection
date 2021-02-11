package com.hendalqett.wifidetection

import android.app.Application
import org.koin.android.ext.android.startKoin

/**
 * Created by hend on 10/15/18.
 */
class WifiDetectionApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // start Koin context
        startKoin(this, listOf(appModule))
    }
}
