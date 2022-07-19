package com.example.fiverrmorbedpractice.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.fiverrmorbedpractice.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

const val Stop = "stop-service"
class MyFgService:Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent!=null && Stop.equals(intent.action)){
            stopForeground(false)
            stopSelf()
            return START_NOT_STICKY
        }
        try {
            CoroutineScope(Dispatchers.Main).launch {
                while (true){
                    val t = System.currentTimeMillis()
                    val s = SimpleDateFormat("HH:MM:ss", Locale.getDefault())
                    Log.e("Naser" , "Service is running....$startId $flags ${s.format(t)}")
                    delay(1000)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
        val intent2 = Intent(this , MyFgService::class.java)
        intent2.action = Stop
val pendingIntent = PendingIntent.getService(this , 0,intent2,PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        //start frogroungd is a must other wise it will act like a normal service .
        startForeground(100 ,showNotification(this.applicationContext, pendingIntent = pendingIntent))
        return super.onStartCommand(intent, flags, startId)
    }
    private fun showNotification(context: Context , pendingIntent: PendingIntent):Notification{
        val manager  = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelID = "channel id"
        val notificationChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel( channelID, "channel_name" , NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        } else {
            //asdf
        }
        val builder = NotificationCompat.Builder(context ,channelID)
            .setContentTitle("Forground Service")
            .setContentText("running")
            .setSmallIcon(R.drawable.image1)
            .addAction(NotificationCompat.Action.Builder(R.drawable.ic_launcher_background,"Stop" ,pendingIntent).build())

        return builder.build()
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}