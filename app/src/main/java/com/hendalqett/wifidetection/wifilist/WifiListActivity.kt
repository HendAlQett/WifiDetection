package com.hendalqett.wifidetection.wifilist

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.hend.airlines.ui.base.BaseActivity
import com.hendalqett.wifidetection.R
import com.hendalqett.wifidetection.data.WifiNetwork
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener
import kotlinx.android.synthetic.main.activity_main.*


class WifiListActivity : BaseActivity<WifiListPresenter>(), WifiListContract.View, WifiAdapter.OnItemClickedListener, WifiAdapter.OnButtonClickedListener {


    private var wifiReceiver: WifiReceiver? = null

    private lateinit var strongNetworksAdapter: RecyclerView.Adapter<*>
    private lateinit var weakNetworksAdapter: RecyclerView.Adapter<*>

    private lateinit var strongNetworksList: MutableList<Any>
    private lateinit var weakNetworksList: MutableList<Any>


    override fun afterInflation(savedInstanceState: Bundle?) {

        presenter = WifiListPresenter(this)

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
            val snackbarPermissionListener = SnackbarOnDeniedPermissionListener.Builder
                    .with(container, getString(R.string.please_grant_location))
                    .withOpenSettingsButton(getString(R.string.settings)).build()

            Dexter.withActivity(this)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(snackbarPermissionListener).check()
        }
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this@WifiListActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
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

            if (wifi?.scanResults?.size!! > 0) {
                groupEmptyView.visibility = View.GONE
                groupRecyler.visibility = View.VISIBLE
            }

            presenter.getListOFAllWifi(wifi?.scanResults)

        }
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
        //TODO: open details
    }

    override fun onClicked() {
        presenter.getListOf6TopWeakWifi()
    }

    companion object {
        private var wifi: WifiManager? = null
    }
}
