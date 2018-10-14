package com.hendalqett.wifidetection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;

import java.util.List;

/**
 * Created by hend on 10/13/18.
 */
public class Wifi
{
    private Context context = null;
    private static WifiManager wifi;
    private WifiReceiver wifiReceiver;
    public static List<String> WiFiNetworks;

    public Wifi(Context ctx)
    {
        this.context = ctx;
    }

    public void GetWifiNetworks()
    {

    }

    class WifiReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            List<ScanResult> scanwifinetworks = wifi.getScanResults();
            for (ScanResult wifiNetwork:scanwifinetworks) {
                WiFiNetworks.add(wifiNetwork.BSSID);
            }
        }
    }
}