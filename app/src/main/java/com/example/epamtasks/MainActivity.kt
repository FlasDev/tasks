package com.example.epamtasks

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.epamtasks.ui.list.ContactListFragment
import com.example.epamtasks.ui.other.RequiredPermissionFragment

class MainActivity : AppCompatActivity() {

    private lateinit var chargingReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBroadcast()
        checkPermission()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
        == PackageManager.PERMISSION_GRANTED){
            setFragment(ContactListFragment.newInstance())
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS),
                    PERMISSION_READ_CONTACTS)
            }else{
                setFragment(ContactListFragment.newInstance())
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_READ_CONTACTS){
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                checkPermission()
            }
        }
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun initBroadcast() {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        chargingReceiver = ChargingReceiver()

        registerReceiver(chargingReceiver, intentFilter)

        LocalBroadcastManager.getInstance(this)
            .registerReceiver(customReceiver, IntentFilter(SWITCH_ACTION))
    }

    private val customReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context?, action: Intent?) {
            intent.extras?.getInt(SWITCH_EXTRA)?.let {
                Toast.makeText(this@MainActivity, "$it", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setChargeTurnOn() {
        Toast.makeText(this, "Turn On", Toast.LENGTH_SHORT)
            .show()
    }

    private fun setChargeTurnOff() {
        Toast.makeText(this, "Turn Off", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroy() {
        unregisterReceiver(chargingReceiver)
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(customReceiver)
        super.onDestroy()
    }

    companion object {
        private const val PERMISSION_READ_CONTACTS = 0

        const val SWITCH_ACTION = "switch charge"
        const val SWITCH_EXTRA = "switch extra"
    }
}
