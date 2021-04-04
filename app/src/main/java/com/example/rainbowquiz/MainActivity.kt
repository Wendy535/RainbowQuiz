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
    private var tip1 = ""
    private var tip2 = ""
    private var userGuess = ""
    private var letter: Char = ' '

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextUserInput: AppCompatEditText =
            findViewById<AppCompatEditText>(R.id.editTextUserInput)
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
                        number = (0..9).random()
                        letter = colourForQuiz[number]
                        for (num in (0..9)) {
                            if (num == number) {
                                tip1 += letter
                            }
                            else {
                                tip1 += '*'
                            }

                        }
                        textViewResult.text = tip1
                        counter++

                    }
                    2 -> {
                        number = (0..9).random()
                        letter = colourForQuiz[number]
                        for (x in 0..9) {
                            if (x == number) {
                                tip2 += letter
                            }
                            if (tip1[x] != '*') {
                                tip2 += tip1[x]
                            }
                            else {
                                tip2 +='*'
                            }
                        }
                        textViewResult.text = tip2
                        counter++
                    }
                    else -> {
                        textViewResult.text = "Увы, вы проиграли("
                    }
                }
            }
        }

        buttonRestart.setOnClickListener {
            chosenColour = rainbow.random()
            counter = 1
            val textViewResult = findViewById<TextView>(R.id.textView)
            textViewResult.text = hiddenColour
            tip1 = ""
            tip2 = ""
        }

    }

}