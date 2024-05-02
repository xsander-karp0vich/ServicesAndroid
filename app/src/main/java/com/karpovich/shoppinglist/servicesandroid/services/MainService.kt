package com.karpovich.shoppinglist.servicesandroid.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainService: Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("SERVICE_MAIN", "MainService: onCreate()")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("SERVICE_MAIN", "MainService: onStartCommand()")
        coroutineScope.launch {
            for (i in 0..100) {
                delay(400)
                log("i = $i")
            }
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d("SERVICE_MAIN", "MainService: onDestroy()")
        coroutineScope.cancel()
        super.onDestroy()
    }

    private fun log(msg: String) {
        Log.d("SERVICE_MAIN", "MainService: log() $msg")
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainService::class.java)
        }

    }

}