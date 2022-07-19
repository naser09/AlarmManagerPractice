package com.example.fiverrmorbedpractice.alarm_manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.fiverrmorbedpractice.AutoStartActivity
import com.example.fiverrmorbedpractice.R

class MyAlarmReceiver: BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            val service = Intent(context , MyService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context!!.startForegroundService(service)
            }else{
                println("lower")
            }
            println("naser notify call")
            val intent2 = Intent(context , AutoStartActivity::class.java)
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startForegroundService(service)
            showNotification(context)
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