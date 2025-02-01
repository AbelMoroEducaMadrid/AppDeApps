package com.example.appfinalprimeraeva

import android.content.Intent
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * Actividad que permite controlar la linterna del dispositivo, encenderla, apagarla
 * y hacerla parpadear.
 */
class Linterna : AppCompatActivity() {
    
    // Variables que controlan el estado de la linterna
    private var isFlashOn = false  // Estado de la linterna (encendida o apagada)
    private var isBlinking = false  // Estado del parpadeo de la linterna
    
    // Objetos necesarios para controlar la linterna
    private lateinit var cameraManager: CameraManager  // Controlador de la cámara
    private lateinit var cameraId: String  // ID de la cámara
    private lateinit var handler: Handler  // Handler para manejar el parpadeo

    /**
     * Método que se ejecuta cuando se crea la actividad.
     * Inicializa los botones y sus respectivas funcionalidades para controlar la linterna.
     *
     * @param savedInstanceState El estado guardado de la actividad si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linterna)

        // Referencias a los botones de la interfaz de usuario
        val btnToggleFlash = findViewById<Button>(R.id.btnToggleFlash)  // Botón para encender/apagar la linterna
        val btnBlinkFlash = findViewById<Button>(R.id.btnBlinkFlash)  // Botón para iniciar/parar el parpadeo
        val btnVolver = findViewById<Button>(R.id.btnVolver)  // Botón para volver a MainActivity

        // Inicializa CameraManager y obtiene el ID de la cámara
        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        cameraId = cameraManager.cameraIdList[0]  // Selecciona la primera cámara disponible
        handler = Handler(Looper.getMainLooper())

        // Acción al hacer clic en el botón para encender o apagar la linterna
        btnToggleFlash.setOnClickListener {
            toggleFlash()
        }

        // Acción al hacer clic en el botón para iniciar o detener el parpadeo de la linterna
        btnBlinkFlash.setOnClickListener {
            toggleBlink()
        }

        // Acción al hacer clic en el botón para volver a MainActivity
        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**
     * Enciende o apaga la linterna dependiendo de su estado actual.
     */
    private fun toggleFlash() {
        try {
            // Cambiar el estado de la linterna
            isFlashOn = !isFlashOn
            cameraManager.setTorchMode(cameraId, isFlashOn)
            val status = if (isFlashOn) "encendida" else "apagada"
            Toast.makeText(this, "Linterna $status", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            // Mostrar un mensaje de error si no se puede controlar la linterna
            Toast.makeText(this, "Error al controlar la linterna", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Inicia o detiene el parpadeo de la linterna según su estado actual.
     */
    private fun toggleBlink() {
        if (isBlinking) {
            // Detener el parpadeo
            isBlinking = false
            handler.removeCallbacksAndMessages(null)
            cameraManager.setTorchMode(cameraId, false)  // Apagar la linterna al detener el parpadeo
            Toast.makeText(this, "Parpadeo detenido", Toast.LENGTH_SHORT).show()
        } else {
            // Iniciar el parpadeo
            isBlinking = true
            startBlinking()
            Toast.makeText(this, "Parpadeo iniciado", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Inicia el parpadeo de la linterna cambiando su estado cada 500 ms.
     */
    private fun startBlinking() {
        if (isBlinking) {
            isFlashOn = !isFlashOn
            cameraManager.setTorchMode(cameraId, isFlashOn)
            handler.postDelayed({ startBlinking() }, 500)  // Cambia el estado de la linterna cada 500 ms
        }
    }

    /**
     * Método que se ejecuta cuando la actividad es destruida.
     * Asegura que la linterna se apague si estaba encendida o parpadeando.
     */
    override fun onDestroy() {
        super.onDestroy()
        if (isFlashOn || isBlinking) {
            handler.removeCallbacksAndMessages(null)
            cameraManager.setTorchMode(cameraId, false)
        }
    }
}
