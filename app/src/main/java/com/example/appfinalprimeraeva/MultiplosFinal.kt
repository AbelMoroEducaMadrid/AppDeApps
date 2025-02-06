package com.example.appfinalprimeraeva

import android.os.Bundle
import android.content.Intent
import android.content.res.ColorStateList
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appfinalprimeraeva.databinding.ActivityMultiplosFinalBinding

/**
 * Activity que muestra el resultado de si el número introducido es múltiplo de 3 y/o 5.
 * Dependiendo del resultado, se muestra un mensaje, una animación y un ícono correspondiente.
 */
class MultiplosFinal : AppCompatActivity() {
    // Variable para enlazar las vistas
    private lateinit var binding: ActivityMultiplosFinalBinding

    /**
     * Método de inicialización de la activity. Aquí se configuran las vistas, animaciones,
     * y el cálculo de múltiplos según el número recibido.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el layout y configurar la vista principal
        binding = ActivityMultiplosFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajustar los márgenes de la vista para las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargar la animación de aparición rápida
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_fast)
        binding.main.startAnimation(fadeInAnimation)

        // Obtener el mensaje con el número que se pasó a través del Intent
        val message = intent.getStringExtra("message")
        val number = message?.toIntOrNull()

        // Verificar si el número es múltiplo de 3, 5 o ambos
        if (number != null) {
            val isMultipleOf3 = number % 3 == 0
            val isMultipleOf5 = number % 5 == 0
            val resultMessage: String
            val animationResId: Int
            val iconResId: Int
            var colorResId = R.color.verde

            when {
                isMultipleOf3 && isMultipleOf5 -> {
                    resultMessage = "Es múltiplo de 3 y 5"
                    animationResId = R.anim.pulse_explosive
                    iconResId = R.drawable.laugh_squint
                }

                isMultipleOf3 -> {
                    resultMessage = "Es múltiplo de 3"
                    animationResId = R.anim.pulse
                    iconResId = R.drawable.smile_beam
                }

                isMultipleOf5 -> {
                    resultMessage = "Es múltiplo de 5"
                    animationResId = R.anim.pulse
                    iconResId = R.drawable.smile_beam
                }

                else -> {
                    resultMessage = "No es múltiplo de 3 ni de 5"
                    animationResId = R.anim.fall
                    iconResId = R.drawable.sad
                    colorResId = R.color.rojo
                }
            }

            // Mostrar el resultado y aplicar las animaciones correspondientes
            binding.messageTextView.text = resultMessage
            binding.emoticonImageView.startAnimation(
                AnimationUtils.loadAnimation(this, animationResId)
            )
            binding.emoticonImageView.setImageResource(iconResId)
            val color = ContextCompat.getColor(this, colorResId)
            binding.emoticonImageView.setImageTintList(ColorStateList.valueOf(color))
        } else {
            binding.messageTextView.text = "Mensaje no válido"
        }

        // Configurar el botón "Repetir" para volver a la pantalla de cálculo
        binding.repeatButton.setOnClickListener {
            val intent = Intent(this, MultiplosSecond::class.java)
            startActivity(intent)
        }

        // Configurar el botón "Salir" para regresar a la pantalla principal
        binding.exitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
