package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.wordle.FourLetterWordList.getRandomFourLetterWord


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var counter = 0
        var wordToGuess = ""


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessButton = findViewById<Button>(R.id.button)
        val input1 = findViewById<TextView>(R.id.input1_textView)
        val input2 = findViewById<TextView>(R.id.input2_textView)
        val input3 = findViewById<TextView>(R.id.input3_textView)
        val answer1 = findViewById<TextView>(R.id.answer1_textView)
        val answer2 = findViewById<TextView>(R.id.answer2_textView)
        val answer3 = findViewById<TextView>(R.id.answer3_textView)

        val revealAnswer = findViewById<TextView>(R.id.theWord_textView)

        val userInputText = findViewById<EditText>(R.id.userInput)


        /**
         * Parameters / Fields:
         *   wordToGuess : String - the target word the user is trying to guess
         *   guess : String - what the user entered as their guess
         *
         * Returns a String of 'O', '+', and 'X', where:
         *   'O' represents the right letter in the right place
         *   '+' represents the right letter in the wrong place
         *   'X' represents a letter not in the target word
         */
        fun checkGuess(guess: String): String {
            var result = ""
            for (i in 0..3) {
                if (guess[i] == wordToGuess[i]) {
                    result += "O"
                } else if (guess[i] in wordToGuess) {
                    result += "+"
                } else {
                    result += "X"
                }
            }
            return result
        }



          guessButton.setOnClickListener {
              counter++
              wordToGuess = getRandomFourLetterWord()
              input1.text = userInputText.getText().toString().uppercase()
              input1.visibility = View.VISIBLE
              answer1.text = checkGuess(input1.text as String)
              answer1.visibility = View.VISIBLE
              userInputText.getText().clear()

              if (answer1.text != "OOOO") {
                  guessButton.setOnClickListener {
                      counter++
                      input2.text = userInputText.getText().toString().uppercase()
                      input2.visibility = View.VISIBLE
                      answer2.text = checkGuess(input2.text as String)
                      answer2.visibility = View.VISIBLE
                      userInputText.getText().clear()

                      if (answer2.text != "OOOO") {
                          guessButton.setOnClickListener {
                              counter++
                              input3.text = userInputText.getText().toString().uppercase()
                              input3.visibility = View.VISIBLE
                              answer3.text = checkGuess(input3.text as String)
                              answer3.visibility = View.VISIBLE
                              userInputText.getText().clear()

                              if (answer2.text != "OOOO") {
                                  revealAnswer.text = wordToGuess.toString()
                                  revealAnswer.visibility = View.VISIBLE
                              }
                              else {
                                  revealAnswer.text = wordToGuess
                                  revealAnswer.visibility = View.VISIBLE
                              }
                          }
                      }
                      else {
                          revealAnswer.text = wordToGuess
                          revealAnswer.visibility = View.VISIBLE
                      }
                  }
              }
              else
              {
                  revealAnswer.text = wordToGuess.toString()
                  revealAnswer.visibility = View.VISIBLE
              }
          }
      }
    }
