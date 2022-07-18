package com.example.fiverrmorbedpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AutoStartActivity : AppCompatActivity() {
    private val hideHandler = Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize() , contentAlignment = Alignment.Center){
                Text(text = "Auto Start Screen")
                Button(modifier = Modifier.padding(20.dp).align(Alignment.BottomCenter), onClick = {
                    val intent = Intent(this@AutoStartActivity , MainActivity::class.java)
                    this@AutoStartActivity.startActivity(intent)
                }) {
                    Text(text = "Goto Main Activity")
                }
            }
        }
    }
}