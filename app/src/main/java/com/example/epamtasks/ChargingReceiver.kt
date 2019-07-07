package com.example.epamtasks

import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ChargingReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val newIntent = Intent(MainActivity.SWITCH_ACTION)
        when (intent.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                newIntent.putExtra(MainActivity.SWITCH_EXTRA, CONNECTED)
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                newIntent.putExtra(MainActivity.SWITCH_EXTRA, DISCONNECTED)
            }
        }


        context.sendBroadcast(newIntent)

    }

    companion object {
        const val CONNECTED = 1
        const val DISCONNECTED = 0
    }
}
