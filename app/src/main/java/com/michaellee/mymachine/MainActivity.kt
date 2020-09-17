package com.michaellee.mymachine

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.random
import kotlin.system.measureTimeMillis


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //the one function that View.OnClickListener is onClick{v: View!}
        //this lamda below is implementing that one function onClick without really mentioning it
        //explicitly, the one parameter is referenced by "it". So to access that view, I can use "it"

        //You can use a  lamda to implement a one-function interface
        //onClick(view: View) is the function that is being implemented by the lamda
        // when there's one parameter in the function, "it" is used to refer to that parameter
        //${} is a string template
        button_main_lookbusy.setOnClickListener {
            Toast.makeText(
                this, "Just clicking this button makes me look ${(it as Button).text.toString()}.",
                Toast.LENGTH_SHORT
            ).show()
        }

        // to listen to a switch, you can use the OnCheckChangeListener

        switch_main_useless.setOnCheckedChangeListener { compoundButton, isChecked ->
            // 1. toast the status of the button {checked, or not checked}
            val message = if (isChecked) "Switch is ON" else "Switch if OFF"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            // 2. if the button is checked, uncheck it
            if (isChecked) {
                var timeToWait = (Math.random() * 10000 + 1000).toLong()
                val uncheckTimer = object : CountDownTimer(timeToWait, 1000) {


                    override fun onFinish() {
                        switch_main_useless.isChecked = false
                        layout_main.setBackgroundColor(
                            Color.rgb((0..255).random(), (0..255).random(), (0..255).random())
                        )
                    }

                    override fun onTick(millisRemaining: Long) {
                        if (switch_main_useless.isChecked == false) {
                            cancel()
                        }
                    }

                }
                uncheckTimer.start()
            }
        }

        button_main_selfdestruct.setOnClickListener {
            var isDetonated = true
            button_main_selfdestruct.isEnabled = true
            button_main_selfdestruct.isClickable = true
            val message2 = if (isDetonated) "Button has been pushed" else "Button is not pushed"
            Toast.makeText(this, message2, Toast.LENGTH_SHORT).show()

            if (isDetonated) {
                button_main_selfdestruct.isEnabled = false
                button_main_selfdestruct.isClickable = false
                button_main_lookbusy.isEnabled = false
                button_main_lookbusy.isClickable = false
                switch_main_useless.isEnabled = false
                switch_main_useless.isClickable = false

                val detonationTimer = object : CountDownTimer(10000, 500) {
                    override fun onFinish() {
                        finish()
                    }

                    override fun onTick(millisRemaining: Long) {
                        var timeRemaining = (millisRemaining / 500).toInt()
                        button_main_selfdestruct.text = (timeRemaining / 2).toString()
                        Log.d("Self Destruct", "$timeRemaining")

                        if ((timeRemaining / 2).toInt() > 5) {
                            if ((timeRemaining / 2) % 2 == 1)
                                layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                            else
                                layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                        } else {
                            timeRemaining = (millisRemaining / 250).toInt()
                            if ((timeRemaining / 2) % 2 == 1)
                                layout_main.setBackgroundColor(Color.rgb(255, 255, 255))
                            else
                                layout_main.setBackgroundColor(Color.rgb(255, 0, 0))
                        }
                    }
                }
                detonationTimer.start()
            }
        }
    }
}


