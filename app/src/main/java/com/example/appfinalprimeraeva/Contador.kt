package com.example.appfinalprimeraeva

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

/**
 * Actividad que implementa un contador con funcionalidades de incremento, decremento y reinicio.
 * Además, permite cambiar el color y la fuente del contador de manera aleatoria.
 */
class Contador : AppCompatActivity() {

    // Contador inicializado en 0
    private var contador = 0
    
    // Arrays para colores y fuentes disponibles
    private lateinit var colorArray: IntArray
    private lateinit var fontArray: Array<String>

    // Variables para almacenar el color y la fuente actuales
    private var currentColor: Int = 0
    private var currentFont: String = ""

    // Etiqueta para registros de Log
    private val TAG = "ESTADOS_CONTADOR"

    /**
     * Método llamado cuando se crea la actividad.
     * Configura el contador, la fuente, el color, y los botones de la interfaz.
     * 
     * @param savedInstanceState El estado guardado de la actividad si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contador)

        // Referencia al TextView que muestra el contador
        val textViewContador = findViewById<TextView>(R.id.textViewContador)

        // Inicialización de los arrays de colores y fuentes desde recursos
        colorArray = resources.getIntArray(R.array.colorArray)
        fontArray = resources.getStringArray(R.array.fontArray)

        // Botón para volver a la actividad principal
        val btVolver = findViewById<Button>(R.id.btVolver)
        btVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Restaurar el estado del contador, color y fuente si se ha guardado previamente
        if (savedInstanceState != null) {
            contador = savedInstanceState.getInt("CONTADOR")
            currentColor = savedInstanceState.getInt("COLOR")
            currentFont = savedInstanceState.getString("FONT") ?: fontArray[0]

            // Aplicar los valores restaurados
            textViewContador.text = contador.toString()
            textViewContador.setTextColor(currentColor)
            textViewContador.typeface = Typeface.create(currentFont, Typeface.NORMAL)
        } else {
            // Si no hay estado guardado, se inicializa con valores predeterminados
            currentColor = resources.getColor(android.R.color.black, null)
            currentFont = fontArray[0]
            textViewContador.text = contador.toString()
        }

        // Botones para controlar el contador
        val btAdd = findViewById<Button>(R.id.btAdd)
        val btSub = findViewById<Button>(R.id.btSub)
        val btReset = findViewById<Button>(R.id.btReset)

        /**
         * Cambia el color y la fuente del contador de manera aleatoria.
         */
        fun randomizeFont() {
            val randomColor = colorArray[Random.nextInt(colorArray.size)]
            val randomFont = fontArray[Random.nextInt(fontArray.size)]

            currentColor = randomColor
            currentFont = randomFont

            textViewContador.setTextColor(randomColor)
            textViewContador.typeface = Typeface.create(randomFont, Typeface.NORMAL)
        }

        // Incrementa el contador y cambia el estilo
        btAdd.setOnClickListener {
            contador++
            textViewContador.text = contador.toString()
            randomizeFont()
        }

        // Decrementa el contador y cambia el estilo
        btSub.setOnClickListener {
            contador--
            textViewContador.text = contador.toString()
            randomizeFont()
        }

        // Reinicia el contador a 0 y restablece el color y la fuente
        btReset.setOnClickListener {
            contador = 0
            textViewContador.text = contador.toString()
            currentColor = resources.getColor(android.R.color.black, null)
            currentFont = fontArray[0]
            textViewContador.setTextColor(currentColor)
            textViewContador.typeface = Typeface.create(currentFont, Typeface.NORMAL)
        }

        Log.d(TAG, "En el método onCreate")
    }

    /**
     * Método llamado cuando la actividad está visible en primer plano.
     * 
     * Realiza las operaciones necesarias cuando la actividad se vuelve visible.
     */
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "En el método onStart")
    }

    /**
     * Método llamado cuando la actividad comienza a interactuar con el usuario.
     * 
     * Realiza las operaciones necesarias cuando la actividad está lista para interactuar.
     */
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "En el método onResume")
    }

    /**
     * Método llamado cuando la actividad está a punto de ser destruida.
     * 
     * Realiza las operaciones necesarias para liberar recursos antes de destruir la actividad.
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "En el método onDestroy")
    }

    /**
     * Método llamado cuando la actividad pierde el enfoque pero aún permanece visible.
     * 
     * Realiza las operaciones necesarias para manejar la pausa de la actividad.
     */
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "En el método onPause")
    }

    /**
     * Método llamado cuando la actividad ya no está visible en la pantalla.
     * 
     * Realiza las operaciones necesarias para manejar la detención de la actividad.
     */
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "En el método onStop")
    }

    /**
     * Guarda el estado actual de la actividad para restaurarlo más tarde.
     * 
     * @param estadoAGuardar El `Bundle` en el que se deben almacenar los valores.
     */
    override fun onSaveInstanceState(estadoAGuardar: Bundle) {
        super.onSaveInstanceState(estadoAGuardar)
        Log.d(TAG, "onSaveInstanceState. Guardo contador con valor " + contador.toString())

        // Guardar el contador, color y fuente
        estadoAGuardar.putInt("CONTADOR", contador)
        estadoAGuardar.putInt("COLOR", currentColor)
        estadoAGuardar.putString("FONT", currentFont)
    }

    /**
     * Restaura el estado de la actividad desde el `Bundle` guardado previamente.
     * 
     * @param estadoARestaurar El `Bundle` que contiene los valores restaurados.
     */
    override fun onRestoreInstanceState(estadoARestaurar: Bundle) {
        super.onRestoreInstanceState(estadoARestaurar)
        contador = estadoARestaurar.getInt("CONTADOR")
        currentColor = estadoARestaurar.getInt("COLOR")
        currentFont = estadoARestaurar.getString("FONT") ?: fontArray[0]

        Log.d(TAG, "onRestoreInstanceState. Restauro al contador el valor " + contador.toString())

        val textViewContador: TextView = findViewById(R.id.textViewContador)
        textViewContador.text = contador.toString()
        textViewContador.setTextColor(currentColor)
        textViewContador.typeface = Typeface.create(currentFont, Typeface.NORMAL)
    }
}
