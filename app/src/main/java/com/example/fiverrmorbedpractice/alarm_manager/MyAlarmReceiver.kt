package com.example.fiverrmorbedpractice.alarm_manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.fiverrmorbedpractice.R

class MyAlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            println("naser notify call")
            showNotification(context!!)
            context.startActivity(intent)
        }catch (ex:Exception){
            println("naser exeption $ex")
        }
    }
    private fun showNotification(context: Context){
        val manager  = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelID = "channel id"
        val notificationChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel( channelID, "channel_name" , NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        } else {
            //asdf
        }

        val builder = NotificationCompat.Builder(context ,channelID)
            .setContentTitle("title")
            .setContentText("text")
            .setSmallIcon(R.drawable.image1)
        manager.notify(1 , builder.build())
    }
}