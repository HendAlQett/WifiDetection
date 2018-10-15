package com.hendalqett.wifidetection.utils

import android.app.Activity
import android.widget.Toast

/**
 * Created by hend on 10/14/18.
 */
class ErrorMessagesHandler{
    companion object {
        fun showMessageShortToast(activity: Activity?, message: String?) {
            if (activity != null) {
                if (message != null) {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Unknown error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
