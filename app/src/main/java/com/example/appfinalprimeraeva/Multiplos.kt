package com.example.appfinalprimeraeva

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import com.example.appfinalprimeraeva.databinding.ActivityMultiplosBinding

/**
 * Activity que muestra la pantalla principal para el cálculo de múltiplos.
 * Presenta un mensaje de bienvenida y permite al usuario decidir si desea continuar con el cálculo.
 */
class Multiplos : AppCompatActivity() {

    // Variable para enlazar las vistas
    private lateinit var binding: ActivityMultiplosBinding

    /**
     * Método de inicialización de la activity. Aquí se configuran las vistas, animaciones
     * y los listeners para los botones.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflar el layout y configurar la vista principal
        binding = ActivityMultiplosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ajustar los márgenes de la vista para tener en cuenta las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cargar la animación de aparición rápida
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_fast)
        binding.main.startAnimation(fadeInAnimation)

        // Configurar el botón "Sí" para navegar a la siguiente pantalla
        binding.buttonYes.setOnClickListener {
            val intent = Intent(this, MultiplosSecond::class.java)
            startActivity(intent)
        }

        // Configurar el botón "No" para regresar a la pantalla principal
        binding.buttonNo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
