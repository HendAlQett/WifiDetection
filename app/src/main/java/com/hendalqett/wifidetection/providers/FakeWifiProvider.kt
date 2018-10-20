package com.hendalqett.wifidetection.providers

import android.os.CountDownTimer
import com.hendalqett.wifidetection.data.model.WifiNetwork
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by hend on 10/19/18.
 */
class FakeWiFiProvider(private val callback: WifiProvider.Callback) : WifiProvider {

    private val countDownTimer = object : CountDownTimer(Long.MAX_VALUE, TimeUnit.SECONDS.toMillis(5)) {
        override fun onFinish() {
            cancel()
        }

        override fun onTick(p0: Long) {
            val random = Random()
            callback.onFarAwayUpdate(generateFakeWiFis(random.nextInt(10)))
        }
    }

    override fun start() {
        countDownTimer.start()
    }

    override fun stop() {
        countDownTimer.onFinish()
    }

    private fun generateFakeWiFis(numberOfWifis: Int) : List<WifiNetwork> {
        val list = mutableListOf<WifiNetwork>()

        for (i in 1..numberOfWifis) {
            list.add(WifiNetwork(UUID.randomUUID().toString()))
        }

        return list
    }
}
