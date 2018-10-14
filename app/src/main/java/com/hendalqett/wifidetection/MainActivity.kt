package com.hendalqett.wifidetection

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), WifiAdapter.OnItemClickedListener, WifiAdapter.OnButtonClickedListener {


    private var wifiReceiver: WifiReceiver? = null

    private lateinit var strongNetworksAdapter: RecyclerView.Adapter<*>
    private lateinit var weakNetworksAdapter: RecyclerView.Adapter<*>

    private lateinit var strongNetworksList: MutableList<Any>
    private lateinit var weakNetworksList: MutableList<Any>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WiFiNetworks = ArrayList()

        strongNetworksList = ArrayList()
        weakNetworksList = ArrayList()

        strongNetworksAdapter = WifiAdapter(strongNetworksList, this@MainActivity, null)
        weakNetworksAdapter = WifiAdapter(weakNetworksList, this@MainActivity, this@MainActivity)

        recyclerViewStrongNetworks.setHasFixedSize(true)
        recyclerViewStrongNetworks.adapter = strongNetworksAdapter
        recyclerViewWeakNetworks.setHasFixedSize(true)
        recyclerViewWeakNetworks.adapter = weakNetworksAdapter


    }

    override fun onStart() {
        super.onStart()
        if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            val snackbarPermissionListener = SnackbarOnDeniedPermissionListener.Builder
                    .with(container, "You should grant location permission for this app to work")
                    .withOpenSettingsButton("Settings")
                    .withCallback(object : Snackbar.Callback() {
                        override fun onShown(snackbar: Snackbar?) {
                            // Event handler for when the given Snackbar is visible
                        }

                        override fun onDismissed(snackbar: Snackbar?, event: Int) {
                            // Event handler for when the given Snackbar has been dismissed
                        }
                    }).build()

            Dexter.withActivity(this)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(snackbarPermissionListener).check()
        }
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

            // Start a scan and register the Broadcast receiver to get the list of Wifi Networks
            wifiReceiver = WifiReceiver()
            registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
            wifi?.startScan()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        if (wifiReceiver != null)
            unregisterReceiver(wifiReceiver)
    }

    internal inner class WifiReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            WiFiNetworks.clear()
            wifi?.scanResults?.forEach { wifiNetwork ->

                var network = WifiNetwork(wifiNetwork.SSID, wifiNetwork.level)
                println(wifiNetwork.SSID)
                println(wifiNetwork.level)

                if (!TextUtils.isEmpty(network.name)) {
                    WiFiNetworks.add(network)
                }

            }

            WiFiNetworks.sortByDescending { wifiNetwork -> wifiNetwork.level }

            if (WiFiNetworks.size > 0) {
                groupEmptyView.visibility = View.GONE
                groupRecyler.visibility = View.VISIBLE
            }

            getTop3Stong()
            getTop3Weak()


            strongNetworksAdapter.notifyDataSetChanged()
            weakNetworksAdapter.notifyDataSetChanged()


        }
    }


    fun getTop3Stong() {
        strongNetworksList.clear()
        strongNetworksList.add("WiFis closer by")
        strongNetworksList.addAll(WiFiNetworks.take(3))
    }

    fun getTop3Weak() {
        weakNetworksList.clear()
        weakNetworksList.add("WiFis further away")
        weakNetworksList.addAll(WiFiNetworks.takeLast(WiFiNetworks.size - 3).take(3))
        if (weakNetworksList.size == 4) {
            weakNetworksList.add(0)
        }

    }

    fun getTop6Weak() {
        Toast.makeText(this, "Hi", Toast.LENGTH_LONG).show()
        weakNetworksList.clear()
        weakNetworksList.add("WiFis further away")
        weakNetworksList.addAll(WiFiNetworks.takeLast(WiFiNetworks.size - 3).take(6))
        //TODO: make show less button
//        if (weakNetworksList.size == 4) {
//            weakNetworksList.add(0)
//        }

    }

    override fun onClicked(network: WifiNetwork) {
        //TODO: open details activity
        Toast.makeText(this, network.name, Toast.LENGTH_LONG).show()

    }

    override fun onClicked() {
        //TODO: show 6 networks and hide it if the weak networks are 3 or less
        getTop6Weak()
    }

    companion object {
        private var wifi: WifiManager? = null
        lateinit var WiFiNetworks: MutableList<WifiNetwork>
    }
}
