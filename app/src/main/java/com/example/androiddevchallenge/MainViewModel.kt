package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _time = MutableLiveData<Long>(0)
    val time: LiveData<Long> = _time

    private val _isStartEnabled = MutableLiveData(true)
    val isStartEnabled: LiveData<Boolean> = _isStartEnabled

    fun startTimer() {
        val howLong = _time.value ?: 0
        object : CountDownTimer(
            howLong,
            1
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _isStartEnabled.value = false
                _time.value = millisUntilFinished
            }

            override fun onFinish() {
                _time.value = 0
            }
        }.start()
    }

    fun addTime() {
        _time.value = (_time.value ?: 0) + 1000
        _isStartEnabled.value = true
    }

    fun decrementTime() {
        _time.value?.let {
            if (it - 1000 > 0) {
                _time.value = it - 1000
            }
        }
    }
}