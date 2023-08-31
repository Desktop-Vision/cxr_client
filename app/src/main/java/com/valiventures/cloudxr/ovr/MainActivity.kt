
package com.valiventures.cloudxr.ovr

import android.app.NativeActivity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log

class MainActivity : NativeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // do super first, as that sets up some native things.
        super.onCreate(savedInstanceState)
    }

    private fun doResume() {
        //log the launch intent
        Log.d(TAG, "ATTEMPTING TO LAUNCH: $launchUrl")

        val newIntent = Intent("com.oculus.vrshell.intent.action.LAUNCH").apply {
            setPackage("com.oculus.vrshell")
            putExtra("uri", "ovrweb://webtask?uri=" + Uri.encode(launchUrl))
            putExtra("intent_data", Uri.parse("systemux://browser"))
        }

        //LOG
        Log.d(TAG, "SENDING INTENT: $newIntent")

        //send the intent
        sendBroadcast(newIntent)

        //close the app
        //finish()
    }

    override fun onResume() {
        Log.d(TAG, "$this onResume()")
        super.onResume()
        doResume()
    }


    companion object {
        private const val TAG = "CloudXR"
        private const val launchUrl = "https://desktop.vision/app/#/xr?appid=100&xr=true"

        init {
        }

        @JvmStatic
        external fun nativeHandleLaunchOptions(jcmdline: String?)
    }
}