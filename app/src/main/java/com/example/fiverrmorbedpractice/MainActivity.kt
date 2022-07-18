package com.example.fiverrmorbedpractice

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.widget.DigitalClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.fiverrmorbedpractice.alarm_manager.MyAlarmReceiver
import com.example.fiverrmorbedpractice.presentaion.change_image_screen.ChangingImageViewModel
import com.example.fiverrmorbedpractice.presentaion.change_image_screen.ScreenChangeImage
import com.example.fiverrmorbedpractice.ui.theme.FiverrMorbedPracticeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Date
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FiverrMorbedPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val time = remember {
                        mutableStateOf("null")
                    }
                    val cour = rememberCoroutineScope()
                    Column(verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally) {
                        AndroidView(factory = {
                            DigitalClock(it).apply {
                                time.value = this.text.toString()
                            }
                        })
                        Text(text = Date().time.toString())

                        val sf = SimpleDateFormat("HH:MM:ss" , java.util.Locale.getDefault())
                        Text(text = time.value , fontSize = 35.sp)
                        Button(onClick = {
                            cour.launch {
                                while (true){
                                    delay(1000)
                                    time.value = sf.format(Date(System.currentTimeMillis()))
                                }
                            }
                        }) {
                            Text(text = "Start")
                        }
                    }

                    
                }
            }
        }
    }
    fun setAlarm(context: Context) {
        val time = System.currentTimeMillis() + 10000
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MyAlarmReceiver::class.java)
        val pendingIntent =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            } else {
                PendingIntent.getBroadcast(context, 0, intent,0)
            }
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)
    }
}

