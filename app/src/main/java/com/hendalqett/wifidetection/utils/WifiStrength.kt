package com.hendalqett.wifidetection.utils

import com.hendalqett.wifidetection.R

/**
 * Created by hend on 10/15/18.
 */
class WifiStrength{
    companion object {
        fun getSignalImage(level: Int?): Int {

            return when (level) {
                in 0 downTo -50 -> R.drawable.ic_signal_wifi_4_bar_black
                in -51 downTo -60 -> R.drawable.ic_signal_wifi_3_bar_black
                in -61 downTo -70 -> R.drawable.ic_signal_wifi_2_bar_black
                in -71 downTo -80 -> R.drawable.ic_signal_wifi_1_bar_black
                else -> R.drawable.ic_signal_wifi_0_bar_black
            }

        }
    }
}
