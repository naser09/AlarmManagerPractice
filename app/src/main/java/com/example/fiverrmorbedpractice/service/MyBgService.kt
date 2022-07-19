package com.example.fiverrmorbedpractice.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyBgService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            CoroutineScope(Dispatchers.Main).launch {
                while (true){
                    Log.e("Naser" , "Service is running....")
                    delay(1000)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}