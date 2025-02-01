package com.example.appfinalprimeraeva

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appfinalprimeraeva.databinding.ActivityMainBinding

/**
 * Actividad principal que proporciona acceso a otras pantallas de la aplicación.
 */
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding  // Binding para acceder a los elementos de la UI

    /**
     * Método que se ejecuta cuando se crea la actividad principal.
     * Aquí se inicializan los botones y se configuran las acciones de navegación.
     *
     * @param savedInstanceState El estado guardado de la actividad si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navegar a TresBotones
        binding.botonTresBotones.setOnClickListener {
            val intent = Intent(this, TresBotones::class.java)
            startActivity(intent)
        }

        // Navegar a Calculadora
        binding.botonCalculadora.setOnClickListener {
            val intent = Intent(this, Calculadora::class.java)
            startActivity(intent)
        }

        // Navegar a Contador
        binding.botonContador.setOnClickListener {
            val intent = Intent(this, Contador::class.java)
            startActivity(intent)
        }

        // Navegar a Linterna
        binding.botonLinterna.setOnClickListener {
            val intent = Intent(this, Linterna::class.java)
            startActivity(intent)
        }

        // Navegar a Intentos
        binding.botonIntentos.setOnClickListener {
            val intent = Intent(this, Intentos::class.java)
            startActivity(intent)
        }

        // Navegar a Conversor
        binding.botonConversor.setOnClickListener {
            val intent = Intent(this, Conversor::class.java)
            startActivity(intent)
        }

        // Navegar a Ciudades
        binding.botonCiudades.setOnClickListener {
            val intent = Intent(this, Ciudades::class.java)
            startActivity(intent)
        }

        // Navegar a RecyclerViewTest
        binding.botonRecyclerView.setOnClickListener {
            val intent = Intent(this, RecyclerViewTest::class.java)
            startActivity(intent)
        }

        // Navegar a Parques
        binding.botonParques.setOnClickListener {
            val intent = Intent(this, Parques::class.java)
            startActivity(intent)
        }
    }
}
