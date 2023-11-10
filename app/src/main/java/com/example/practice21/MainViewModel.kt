package com.example.practice21

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel: ViewModel() {
    private var isStarted = MutableLiveData(false)
    private var str: MutableLiveData<String>? = null
    private var i: Int = 0

    var msec = 0
    var sec = 0
    var min = 0
    var text = ""

    fun getValue(): LiveData<String>? {
        if (str == null) {
            str = MutableLiveData("00:00.00")
        }
        return str
    }
    fun execute() {



        if (!isStarted.value!!) {
            isStarted.value = true
            isStarted.postValue(true)
            val runnable = Runnable {
                while (isStarted.value!!){
                    i += 1
                    if(i == 100) i = 0
                    msec = i
                    if(msec == 99) {
                        msec = 0
                        sec += 1
                    }
                    if(sec == 60){
                        sec = 0
                        min += 1
                    }
                    try {
                        text = String.format("%02d:%02d.%02d", min, sec, msec)

                        str!!.postValue(text)
                        Thread.sleep(1000/60)
                    }
                    catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
            val thread = Thread(runnable)
            thread.start()
        }
    }
    fun stopTimer(){
        if(isStarted.value!!){
            isStarted.value = false
        }
    }
    fun resetTimer(){
        if(isStarted.value!!){
            isStarted.value = false
        }
        msec = 0
        sec = 0
        min = 0
        text = ""
    }
}