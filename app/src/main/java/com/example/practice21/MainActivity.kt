package com.example.practice21

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    private var start = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val buttonReset: Button = findViewById(R.id.button2)
        val textView: TextView = findViewById(R.id.textViewTime)

        val mainViewModel: MainViewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getValue()?.observe(this) { value ->
            textView.text = value
        }
        button.setOnClickListener {
            if(!start) {
                start = true
                button.setText(R.string.btn_pause)
                mainViewModel.execute()
            }
            else{
                start = false
                button.setText(R.string.btn_start)
                mainViewModel.stopTimer()
            }
        }
        buttonReset.setOnClickListener {
            if(start) {
                start = false
                button.setText(R.string.btn_start)
            }
            mainViewModel.resetTimer()
            textView.text = "00:00.00"
        }

    }


}