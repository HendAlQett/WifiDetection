//package com.hendalqett.wifidetection.data
//
//import com.hendalqett.wifidetection.data.fake.FakeWiFiProvider
//import com.hendalqett.wifidetection.data.model.WifiNetwork
//import com.hendalqett.wifidetection.data.real.RealWiFiProvider
//
///**
// * Created by hend on 10/15/18.
// */
//class WiFiProviderRepository : WifiProvider {
//
//    private lateinit var realWiFiProvider: RealWiFiProvider
//    private lateinit var fakeWiFiProvider: FakeWiFiProvider
//
//    private fun FixturesRepository(realWiFiProvider: RealWiFiProvider,
//                                   fakeWiFiProvider: FakeWiFiProvider) {
//        this.realWiFiProvider = realWiFiProvider
//        this.fakeWiFiProvider = fakeWiFiProvider
//    }
//    override fun start() {
//        realWiFiProvider.start()
//        fakeWiFiProvider.start()
//        var wifiNetworks: MutableList<WifiNetwork> = ArrayList()
//        wifiNetworks.add(WifiNetwork("Hend" - 1))
//        wifiNetworks.add(WifiNetwork("Hend" - 5))
//
//        wifiNetworks.add(WifiNetwork("Hend2", -2))
//
//        wifiNetworks.add(WifiNetwork("Hend", -3))
//        onFarAwayUpdate()
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun stop() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun onFarAwayUpdate(items: List<WifiNetwork>) {
//        //Should call fake wifi
//
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun onCloseByUpdate(items: List<WifiNetwork>) {
//        //Should call real wifi
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//}
