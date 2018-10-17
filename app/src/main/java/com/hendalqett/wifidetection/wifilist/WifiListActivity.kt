package com.hendalqett.wifidetection.wifilist

import android.Manifest
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.hend.airlines.ui.base.BaseActivity
import com.hendalqett.wifidetection.R
import com.hendalqett.wifidetection.data.model.WifiNetwork
import com.hendalqett.wifidetection.receivers.WifiReceiver
import com.hendalqett.wifidetection.utils.PermissionHandler
import com.hendalqett.wifidetection.wifidetails.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class WifiListActivity : BaseActivity<WifiListPresenter>(), WifiListContract.View, WifiAdapter.OnItemClickedListener, WifiAdapter.OnButtonClickedListener, WifiReceiver.WifiReceiverListener {

    private lateinit var wifiReceiver: WifiReceiver
    private lateinit var strongNetworksAdapter: RecyclerView.Adapter<*>
    private lateinit var weakNetworksAdapter: RecyclerView.Adapter<*>
    private lateinit var strongNetworksList: MutableList<Any>
    private lateinit var weakNetworksList: MutableList<Any>

    private val presenter: WifiListPresenter by inject { parametersOf(this) }

    override fun afterInflation(savedInstanceState: Bundle?) {

        strongNetworksList = ArrayList()
        weakNetworksList = ArrayList()

        strongNetworksAdapter = WifiAdapter(strongNetworksList, this@WifiListActivity, null)
        weakNetworksAdapter = WifiAdapter(weakNetworksList, this@WifiListActivity, this@WifiListActivity)

        recyclerViewStrongNetworks.setHasFixedSize(true)
        recyclerViewStrongNetworks.adapter = strongNetworksAdapter
        recyclerViewWeakNetworks.setHasFixedSize(true)
        recyclerViewWeakNetworks.adapter = weakNetworksAdapter
    }


    override fun viewBackButton(): Boolean {
        return false
    }


    override fun setActionbarTitle(): Int {
        return R.string.app_name
    }

    override val layoutResource: Int
        get() = R.layout.activity_main



    override fun onStart() {
        super.onStart()
        if (ActivityCompat.checkSelfPermission(this@WifiListActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            PermissionHandler.permissionForLocation(this, container)
        }
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this@WifiListActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            wifiReceiver = WifiReceiver(this@WifiListActivity)
            registerReceiver(wifiReceiver, IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION))
            wifiReceiver.startScan()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(wifiReceiver)
    }

    override fun onCloseByUpdate(items: List<WifiNetwork>) {
        strongNetworksList.clear()
        strongNetworksList.add(getString(R.string.wifi_near))
        strongNetworksList.addAll(items)
        strongNetworksAdapter.notifyDataSetChanged()
    }

    override fun onFarAwayUpdate(items: List<WifiNetwork>, isShowButton: Boolean) {
        weakNetworksList.clear()
        weakNetworksList.add(getString(R.string.wifi_far))
        weakNetworksList.addAll(items)
        if (weakNetworksList.size == 4 && isShowButton) {
            weakNetworksList.add(0)
        }
        weakNetworksAdapter.notifyDataSetChanged()
    }


    override fun onClicked(network: WifiNetwork) {
        startActivity<DetailsActivity>("WIFI" to network)
    }

    override fun onClicked() {
        presenter.getListOf6TopWeakWifi()
    }

    override fun onWifiScannedResults(results: List<ScanResult>) {
        if (results.isNotEmpty()) {
            groupEmptyView.visibility = View.GONE
            groupRecyler.visibility = View.VISIBLE
            presenter.getListOFAllWifi(results)
        }

    }

}
