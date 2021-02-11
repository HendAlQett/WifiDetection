package com.hendalqett.wifidetection.utils

import android.Manifest
import android.app.Activity
import android.view.View
import com.hendalqett.wifidetection.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener


/**
 * Created by hend on 10/15/18.
 */
class PermissionHandler {

    companion object {
        fun permissionForLocation(activity: Activity, container: View) {
            val snackbarPermissionListener = SnackbarOnDeniedPermissionListener.Builder
                    .with(container, activity.getString(R.string.please_grant_location))
                    .withOpenSettingsButton(activity.getString(R.string.settings)).build()

            Dexter.withActivity(activity)
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(snackbarPermissionListener).check()
        }
    }
}
