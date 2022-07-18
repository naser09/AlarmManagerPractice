package com.example.fiverrmorbedpractice.presentaion.change_image_screen

import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChangingImageViewModel():ViewModel() {
    private val _test:MutableState<Long> = mutableStateOf(0)
    val test get() = _test

    private val _slider:MutableState<Long> = mutableStateOf(0)
    val slider get() = _slider


    private val timer = object : CountDownTimer(100000,3000) {
        override fun onTick(millisUntilFinished: Long) {
            println("naser $millisUntilFinished")
            _slider.value = _slider.value.plus(1)
        }
        override fun onFinish() {
            _slider.value = 0
            println("naser finished")
        }

    }
    init {
        viewModelScope.launch {
            while (true){
                if (_slider.value==3L){
                    _slider.value = 0
                }
                delay(5000)
                _slider.value = _slider.value.plus(1)
            }
        }
    }
    fun startTimer(){
        timer.start()
    }
    fun stopTimer(){
        timer.onFinish()
        timer.cancel()
    }
}