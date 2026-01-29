package com.example.lab02

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var selectedMood = 0
    private lateinit var moodImages: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Находим все ImageView со смайлами
        moodImages = listOf(
            findViewById(R.id.mood1),
            findViewById(R.id.mood2),
            findViewById(R.id.mood3),
            findViewById(R.id.mood4),
            findViewById(R.id.mood5)
        )

        // Обработчики кликов по смайлам
        moodImages.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                selectMood(index)
            }
        }

        // Кнопка сохранения
        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            saveMood()
        }

        // Кнопка отмены
        val clearButton: Button = findViewById(R.id.clearButton)
        clearButton.setOnClickListener {
            clearForm()
        }
    }

    private fun selectMood(index: Int) {
        selectedMood = index + 1
        moodImages.forEachIndexed { i, imageView ->
            if (i <= index) {
                // Выбранное и все предыдущие смайлы
                imageView.setImageResource(android.R.drawable.btn_star_big_on)
            } else {
                // Оставшиеся смайлы
                imageView.setImageResource(android.R.drawable.btn_star_big_off)
            }
        }
    }

    private fun saveMood() {
        val commentEditText: EditText = findViewById(R.id.commentEditText)
        val comment = commentEditText.text.toString()

        if (selectedMood == 0) {
            Toast.makeText(this, "Выберите настроение!", Toast.LENGTH_SHORT).show()
            return
        }

        val message = "Настроение: $selectedMood/5\nКомментарий: $comment"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun clearForm() {
        selectedMood = 0
        moodImages.forEach { imageView ->
            imageView.setImageResource(android.R.drawable.btn_star_big_off)
        }
        val commentEditText: EditText = findViewById(R.id.commentEditText)
        commentEditText.setText("")
    }
}