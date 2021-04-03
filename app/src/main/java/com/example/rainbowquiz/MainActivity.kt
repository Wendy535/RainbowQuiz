package com.example.rainbowquiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText

internal class MainActivity : AppCompatActivity() {

    private val rainbow =
        arrayOf("Красный", "Оранжевый", "Желтый", "Зеленый", "Голубой", "Синий", "Фиолетовый")
    private var chosenColour = rainbow.random()
    private val hiddenColour: String = "**********"
    private val colourForQuiz = chosenColour.padEnd(10, '-')
    private var counter = 1
    private var number: Int = 0
    private var myTip: String = ""
    private var userGuess = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextUserInput: AppCompatEditText = findViewById<AppCompatEditText>(R.id.editTextUserInput)
        val buttonGuess: AppCompatButton = findViewById<AppCompatButton>(R.id.buttonGuess)
        val textViewResult: TextView = findViewById<TextView>(R.id.textView)
        val buttonRestart: Button = findViewById<Button>(R.id.buttonRestart)

        textViewResult.text = hiddenColour

        buttonGuess.setOnClickListener {
            userGuess = editTextUserInput.text.toString()
            if (userGuess == chosenColour) {
                textViewResult.text = "Угадали! Еще раз?"
            } else {
                when (counter) {
                    1 -> {
                        myTip = tipGenerator()
                        textViewResult.text = myTip
                        counter++
                    }
                    2 -> {
                        myTip = tipGenerator()
                        textViewResult.text = myTip
                        counter++
                    }
                    else -> {
                        textViewResult.text = "Увы, вы проиграли("
                    }
                }
            }
        }

        buttonRestart.setOnClickListener {
            counter = 1
            val textViewResult = findViewById<TextView>(R.id.textView)
            textViewResult.text = hiddenColour
        }

    }

    private fun tipGenerator(): String {
        var tip = ""
        number = (0..9).random()
        val letter = colourForQuiz[number]
        for (x in 0..9) {
            if (x == number) {
                tip += letter
            } else {
                tip += "*"
            }
        }
        return tip
    }

}
