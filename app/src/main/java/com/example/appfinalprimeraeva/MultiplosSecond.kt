package com.example.appfinalprimeraeva

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appfinalprimeraeva.databinding.ActivityMultiplosSecondBinding

/**
 * Activity que permite al usuario ingresar un número para comprobar si es múltiplo de 3 o 5.
 * Verifica la validez del número ingresado antes de proceder.
 */
class MultiplosSecond : AppCompatActivity() {

    // Variable para enlazar las vistas
    private lateinit var binding: ActivityMultiplosSecondBinding

    /**
     * Método de inicialización de la activity. Aquí se configuran las vistas, animaciones
     * y el listener para el botón de cálculo.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el layout y configurar la vista principal
        binding = ActivityMultiplosSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajustar los márgenes de la vista para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargar la animación de aparición rápida
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_fast)
        binding.main.startAnimation(fadeInAnimation)

        // Configurar el botón de cálculo para validar el número ingresado
        binding.calculateButton.setOnClickListener {
            validateAndProceed()
        }
    }

    /**
     * Valida el número ingresado por el usuario y lo pasa a la siguiente pantalla
     * si es válido. En caso contrario, muestra un mensaje de error.
     */
    private fun validateAndProceed() {
        val inputText = binding.inputNumber.text.toString()
        val numberRegex = """^-?\d+(\.\d+)?$""".toRegex()

        // Validaciones del campo de entrada
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

            inputText.any() { !it.isDigit() } -> {
                showToastAndClearInput("El texto contiene símbolos")
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

    /**
     * Muestra un mensaje de error mediante un Toast y limpia el campo de entrada.
     * Además, se aplica una animación de sacudida al campo.
     */
    private fun showToastAndClearInput(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        binding.inputNumber.startAnimation(
            AnimationUtils.loadAnimation(this, R.anim.shake_once)
        )
        binding.inputNumber.text.clear()
    }
}
