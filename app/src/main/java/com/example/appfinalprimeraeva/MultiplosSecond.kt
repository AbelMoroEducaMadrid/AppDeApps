package com.example.appfinalprimeraeva

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appfinalprimeraeva.databinding.ActivityMultiplosSecondBinding

class MultiplosSecond : AppCompatActivity() {

    private lateinit var binding: ActivityMultiplosSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMultiplosSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_fast)
        binding.main.startAnimation(fadeInAnimation)

        binding.calculateButton.setOnClickListener {
            validateAndProceed()
        }
    }

    private fun validateAndProceed() {
        val inputText = binding.inputNumber.text.toString()
        val numberRegex = """^-?\d+(\.\d+)?$""".toRegex()

        when {
            inputText.isEmpty() -> {
                showToastAndClearInput("El campo está vacío")
            }

            inputText.any { it.isLetter() } -> {
                showToastAndClearInput("El texto contiene letras")
            }

            inputText.contains(" ") -> {
                showToastAndClearInput("El texto contiene espacios")
            }

            inputText.length > 10 -> {
                showToastAndClearInput("El texto excede los 10 dígitos")
            }

            inputText.matches(numberRegex) -> {
                val number = inputText.toDouble().toInt()
                val intent = Intent(this, MultiplosFinal::class.java)
                intent.putExtra("message", number.toString())
                startActivity(intent)
            }

            else -> {
                showToastAndClearInput("Texto no válido")
            }
        }
    }

    private fun showToastAndClearInput(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        binding.inputNumber.text.clear()
    }
}