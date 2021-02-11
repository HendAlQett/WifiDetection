package com.hendalqett.wifidetection

import com.hendalqett.wifidetection.utils.WifiStrength
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Created by hend on 10/16/18.
 */
class WifiStrengthTest {
    @Test
    fun wifiImageIsCorrect() {
        val image = WifiStrength.getSignalImage(-100)
        assertEquals(image, R.drawable.ic_signal_wifi_0_bar_black)
    }
}
