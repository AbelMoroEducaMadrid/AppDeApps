package com.example.appfinalprimeraeva

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity

/**
 * Actividad que maneja diferentes intentos para realizar acciones como abrir una página web,
 * realizar una llamada telefónica o abrir la configuración del dispositivo.
 */
class Intentos : AppCompatActivity() {

    private lateinit var takePictureLauncher: ActivityResultLauncher<Intent>

    /**
     * Método que se ejecuta cuando se crea la actividad.
     * Configura los botones y las acciones correspondientes.
     *
     * @param savedInstanceState El estado guardado de la actividad si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intentos)

        // Referencias a los botones de la interfaz de usuario
        val btnOpenWeb = findViewById<Button>(R.id.btnOpenWeb)  // Botón para abrir una página web
        val btnDialNumber = findViewById<Button>(R.id.btnDialNumber)  // Botón para marcar un número
        val btnOpenSettings = findViewById<Button>(R.id.btnOpenSettings)  // Botón para abrir la configuración
        val btnReturnToMain = findViewById<Button>(R.id.btnReturnToMain)  // Botón para volver a MainActivity

        // Acción para abrir una página web
        btnOpenWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.google.com")
            startActivity(intent)
        }

        // Acción para abrir el marcador con un número predefinido
        btnDialNumber.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:123456789")
            startActivity(intent)
        }

        // Acción para abrir la configuración del dispositivo
        btnOpenSettings.setOnClickListener {
            val intent = Intent(android.provider.Settings.ACTION_SETTINGS)
            startActivity(intent)
        }

        // Acción para volver a la actividad principal
        btnReturnToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
