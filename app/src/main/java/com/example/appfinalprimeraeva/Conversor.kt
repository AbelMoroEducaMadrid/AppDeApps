package com.example.appfinalprimeraeva

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Actividad que permite realizar la conversión de unidades, en este caso, de kilómetros a millas.
 */
class Conversor : AppCompatActivity() {

    /**
     * Método llamado cuando se crea la actividad.
     * Configura los botones y las acciones para la conversión de unidades.
     * 
     * @param savedInstanceState El estado guardado de la actividad si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)

        // Referencias a los elementos de la UI
        val etInput: EditText = findViewById(R.id.etInput)  // Campo de entrada para el valor a convertir
        val btnConvertir: Button = findViewById(R.id.btnConvertir)  // Botón para realizar la conversión
        val tvResultado: TextView = findViewById(R.id.tvResultado)  // Texto donde se muestra el resultado
        val btnVolver: Button = findViewById(R.id.btnVolver)  // Botón para volver a la actividad principal

        // Acción al hacer clic en el botón de conversión
        btnConvertir.setOnClickListener {
            // Obtener el valor ingresado y convertirlo a Double
            val input = etInput.text.toString().toDoubleOrNull()
            if (input != null) {
                // Realizar la conversión de km a millas (ejemplo)
                val resultado = input * 1.60934
                tvResultado.text = "Resultado: $resultado millas"
            } else {
                // Mostrar un mensaje si el valor ingresado no es válido
                Toast.makeText(this, "Ingresa un valor válido", Toast.LENGTH_SHORT).show()
            }
        }

        // Acción al hacer clic en el botón de volver
        btnVolver.setOnClickListener {
            // Navegar de vuelta a la actividad principal
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
