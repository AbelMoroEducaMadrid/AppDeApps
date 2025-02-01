package com.example.appfinalprimeraeva

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appfinalprimeraeva.databinding.ActivityTresBotonesBinding

/**
 * Actividad que contiene tres botones, cada uno con una acción al hacer clic,
 * y un botón para regresar a la actividad principal.
 */
class TresBotones : AppCompatActivity() {

    private lateinit var binding: ActivityTresBotonesBinding

    /**
     * Método que se llama cuando la actividad es creada.
     * Configura el ViewBinding y las acciones de los botones.
     * 
     * @param savedInstanceState El estado guardado de la actividad si está disponible.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Configurar ViewBinding
        binding = ActivityTresBotonesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar acciones de los botones
        binding.boton1.setOnClickListener {
            binding.textoResultado.text = "Has pulsado el botón 1"
        }

        binding.boton2.setOnClickListener {
            binding.textoResultado.text = "Has pulsado el botón 2"
        }

        binding.boton3.setOnClickListener {
            binding.textoResultado.text = "Has pulsado el botón 3"
        }

        // Navegar a MainActivity
        binding.botonIrMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
