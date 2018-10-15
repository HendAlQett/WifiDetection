package com.hendalqett.wifidetection.wifidetails

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hendalqett.wifidetection.R
import com.hendalqett.wifidetection.data.model.WifiNetwork
import com.hendalqett.wifidetection.utils.WifiStrength
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val wifiNetwork = intent.getBundleExtra("bundle").getParcelable<WifiNetwork>("WIFI")
        textViewWifiName.text = wifiNetwork.name
        supportActionBar?.title = wifiNetwork.name

        imageViewWifi.setImageResource(WifiStrength.getSignalImage(wifiNetwork.level))

    }
}
