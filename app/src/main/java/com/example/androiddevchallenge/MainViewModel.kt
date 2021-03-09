package com.example.androiddevchallenge

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.math.BigDecimal
import java.math.RoundingMode

class MainViewModel: ViewModel() {
    private val _time = MutableLiveData<Long>(0)
    val time: LiveData<String> = Transformations.map(_time) { timeLiveData ->
        val timeFraction = BigDecimal(timeLiveData).divide(BigDecimal(1000)).setScale(2, RoundingMode.UP)
        if (timeFraction < BigDecimal.TEN) {
            "0$timeFraction"
        } else {
            "$timeFraction"
        }
    }

    private val _isTimerStartEnabled = MutableLiveData(false)
    val isTimerStartEnabled: LiveData<Boolean> = _isTimerStartEnabled

    private val _isTimerControllerEnabled = MutableLiveData(true)
    val isTimerControllerEnabled: LiveData<Boolean> = _isTimerControllerEnabled

    private val _isEelVisible = MutableLiveData(false)
    val isEelVisible: LiveData<Boolean> = _isTimerControllerEnabled

    fun startTimer() {
        _isTimerStartEnabled.value = false
        _isTimerControllerEnabled.value = false
        val howLong = (_time.value ?: 0)
        object : CountDownTimer(
            howLong,
            1
        ) {
            override fun onTick(millisUntilFinished: Long) {
                _time.value = millisUntilFinished
            }

            override fun onFinish() {
                _time.value = 0
                _isTimerControllerEnabled.value = true
                _isEelVisible.value = true
            }
        }.start()
    }

    fun addTime() {
        _time.value = (_time.value ?: 0) + 1000
        _isTimerStartEnabled.value = true
        _isTimerControllerEnabled.value = true
        _isEelVisible.value = false
    }

    fun decrementTime() {
        _time.value?.let {
            if (it > 0) {
                _time.value = it - 1000
            }
        }
    }

    private fun timerFormat(timeMillis: Int) {
        val formattedTime = BigDecimal(timeMillis/1000).setScale(2)
    }
}