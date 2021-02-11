package com.hendalqett.wifidetection

import com.hendalqett.wifidetection.utils.FilterNetworksHandler
import junit.framework.Assert
import org.junit.Test

/**
 * Created by hend on 10/16/18.
 */
class FilterNetworksHandlerTest {
    @Test
    fun filterTop() {
        val networks = FilterNetworksHandler.filterTop(2, CreateFakeNetworksTestHelper.createWifiNetworks())
        Assert.assertEquals(networks.size, 2)
    }


    @Test
    fun filterLast() {
        val networks = FilterNetworksHandler.filterLast(2, 2, CreateFakeNetworksTestHelper.createWifiNetworks())
        Assert.assertEquals(networks.size, 2)
    }

}