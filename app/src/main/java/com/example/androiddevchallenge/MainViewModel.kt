package com.example.androiddevchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _time = MutableLiveData(0)
    val time: LiveData<Int> = _time

    fun addTime() {
        _time.value = (_time.value ?: 0) + 1
    }

    fun decrementTime() {
        _time.value = (_time.value ?: 0) - 1
    }
}