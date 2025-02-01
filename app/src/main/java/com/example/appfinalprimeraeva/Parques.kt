package com.example.appfinalprimeraeva

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appfinalprimeraeva.databinding.ActivityParquesBinding

/**
 * Actividad que maneja la interfaz de usuario para la pantalla de parques.
 * Esta actividad contiene elementos interactivos como un ToggleButton, Spinner, SeekBar, CheckBox, etc.
 * También maneja la animación de colores y la configuración de componentes.
 */
class Parques : AppCompatActivity() {
    private lateinit var binding: ActivityParquesBinding

    /**
     * Método que se llama cuando la actividad es creada.
     * Se inicializan los componentes de la interfaz de usuario y se configuran las acciones de los botones.
     *
     * @param savedInstanceState Estado guardado de la actividad, si está disponible.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParquesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configurar componentes de la interfaz
        configurarToggleButton()
        configurarAnimacionDeColor()
        configurarSpinner()
        configurarBotonGuardar()
        configurarBotonVolver()
    }

    /**
     * Configura el comportamiento del ToggleButton.
     * Cambia el color de fondo del botón y realiza animaciones en el texto cuando se activa o desactiva.
     */
    private fun configurarToggleButton() {
        val toggleButton = binding.toggleButtonState
        val textView = binding.textViewWelcome

        toggleButton.backgroundTintList = ColorStateList.valueOf(Color.RED)

        val animation = AnimationUtils.loadAnimation(
            this,
            R.anim.todo
        )

        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                textView.startAnimation(animation)
                toggleButton.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
            } else {
                textView.clearAnimation()
                toggleButton.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
        }
    }

    /**
     * Configura la animación de cambio de color en el texto de bienvenida.
     * La animación cambia el color del texto de verde a rojo de forma repetitiva.
     */
    private fun configurarAnimacionDeColor() {
        val textView = binding.textViewWelcome
        val colorChange = ObjectAnimator.ofObject(
            textView, "textColor", ArgbEvaluator(), Color.GREEN, Color.RED
        )

        colorChange.duration = 1000
        colorChange.repeatCount = ValueAnimator.INFINITE
        colorChange.repeatMode = ValueAnimator.REVERSE
        colorChange.start()
    }

    /**
     * Configura el Spinner con los días de la semana.
     */
    private fun configurarSpinner() {
        val spinner = binding.spinnerOptions
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.dias_semana_array,
            android.R.layout.simple_spinner_item
        )
        spinner.adapter = adapter
    }

    /**
     * Configura el comportamiento del botón de guardar.
     * Al hacer clic, muestra un cuadro de diálogo preguntando si desea guardar los cambios.
     */
    private fun configurarBotonGuardar() {
        binding.buttonSave.setOnClickListener {
            val dialog = android.app.AlertDialog.Builder(this)
            dialog.setTitle("Guardar")
            dialog.setMessage(obtenerEstado())
            dialog.setPositiveButton("Sí") { _, _ ->
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
            }
            dialog.setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "No guardado", Toast.LENGTH_SHORT).show()
            }
            dialog.show()

            guardarEstadoEnLog()
        }
    }

    /**
     * Configura el comportamiento del botón para regresar a la actividad principal.
     */
    private fun configurarBotonVolver() {
        binding.buttonBackToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Obtiene el estado actual de todos los componentes interactivos.
     *
     * @return Una cadena de texto que describe el estado de todos los componentes.
     */
    private fun obtenerEstado(): String {
        val estadoStringBuilder = StringBuilder()

        estadoStringBuilder.append("ESTADO - ToggleButton: ")
        estadoStringBuilder.append(binding.toggleButtonState.isChecked.toString())
        estadoStringBuilder.append("\n")

        estadoStringBuilder.append("ESTADO - Spinner: ")
        estadoStringBuilder.append(binding.spinnerOptions.selectedItem.toString())
        estadoStringBuilder.append("\n")

        estadoStringBuilder.append("ESTADO - Switch: ")
        estadoStringBuilder.append(binding.switchAcceptTerms.isChecked.toString())
        estadoStringBuilder.append("\n")

        estadoStringBuilder.append("ESTADO - SeekBar: ")
        estadoStringBuilder.append(binding.seekBarVolume.progress.toString())
        estadoStringBuilder.append("\n")

        estadoStringBuilder.append("ESTADO - Terminos: ")
        estadoStringBuilder.append(binding.checkBoxTerms.isChecked.toString())
        estadoStringBuilder.append("\n")

        estadoStringBuilder.append("ESTADO - Holita: ")
        estadoStringBuilder.append(binding.checkBoxGreeting.isChecked.toString())
        estadoStringBuilder.append("\n")

        val radioGroupState = when (binding.radioGroupGender.checkedRadioButtonId) {
            binding.radioButtonMale.id -> "Hombre"
            binding.radioButtonFemale.id -> "Mujer"
            binding.radioButtonOther.id -> "Otro"
            else -> "No hay nada seleccionado!"
        }
        estadoStringBuilder.append("ESTADO - RadioGroup: ")
        estadoStringBuilder.append(radioGroupState)
        estadoStringBuilder.append("\n\n")

        estadoStringBuilder.append("¿Estás seguro que quieres guardar?")

        return estadoStringBuilder.toString()
    }

    /**
     * Guarda el estado de los componentes en el log para su depuración.
     */
    private fun guardarEstadoEnLog() {
        Log.d("ESTADO - ToggleButton", binding.toggleButtonState.isChecked.toString())
        Log.d("ESTADO - Spinner", binding.spinnerOptions.selectedItem.toString())
        Log.d("ESTADO - Switch", binding.switchAcceptTerms.isChecked.toString())
        Log.d("ESTADO - SeekBar", binding.seekBarVolume.progress.toString())
        Log.d("ESTADO - Terminos", binding.checkBoxTerms.isChecked.toString())
        Log.d("ESTADO - Holita", binding.checkBoxGreeting.isChecked.toString())

        Log.d(
            "ESTADO - RadioGroup",
            when (binding.radioGroupGender.checkedRadioButtonId) {
                binding.radioButtonMale.id -> "Hombre"
                binding.radioButtonFemale.id -> "Mujer"
                binding.radioButtonOther.id -> "Otro"
                else -> "No hay nada seleccionado!"
            }
        )
    }
}
