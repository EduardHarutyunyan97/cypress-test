package com.example.cypress_test.core

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.LiveData

class ConnectionHandler(private val context: Context) : LiveData<Boolean>() {

    override fun onActive() {
        super.onActive()
        context.registerReceiver(
            networkReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onInactive() {
        super.onInactive()
        context.unregisterReceiver(networkReceiver)
    }

    private val networkReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            intent.extras?.let { extras ->
                val activeNetwork = extras[ConnectivityManager.EXTRA_NETWORK_INFO] as? NetworkInfo
                (activeNetwork != null && activeNetwork.isConnectedOrConnecting).apply {
                    postValue(this)
                }
            }
        }
    }
}