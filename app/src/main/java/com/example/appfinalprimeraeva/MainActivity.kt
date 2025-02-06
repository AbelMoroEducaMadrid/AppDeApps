package com.example.appfinalprimeraeva

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appfinalprimeraeva.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Lista de nombres de botones
    private val buttonNames = listOf(
        "Abrir Tres Botones", "Abrir Calculadora", "Abrir Contador",
        "Abrir Linterna", "Abrir Intentos", "Abrir Conversor",
        "Abrir Ciudades", "Abrir RecyclerView", "Abrir Parques", "Abrir Multiplos"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ButtonAdapter(buttonNames) { buttonName ->
            openActivity(buttonName)
        }

        updateFooterText()
    }

    // Abre la actividad según el botón seleccionado
    private fun openActivity(buttonName: String) {
        val intent = when (buttonName) {
            "Abrir Tres Botones" -> Intent(this, TresBotones::class.java)
            "Abrir Calculadora" -> Intent(this, Calculadora::class.java)
            "Abrir Contador" -> Intent(this, Contador::class.java)
            "Abrir Linterna" -> Intent(this, Linterna::class.java)
            "Abrir Intentos" -> Intent(this, Intentos::class.java)
            "Abrir Conversor" -> Intent(this, Conversor::class.java)
            "Abrir Ciudades" -> Intent(this, Ciudades::class.java)
            "Abrir RecyclerView" -> Intent(this, RecyclerViewTest::class.java)
            "Abrir Parques" -> Intent(this, Parques::class.java)
            "Abrir Multiplos" -> Intent(this, Multiplos::class.java)
            else -> null
        }
        intent?.let { startActivity(it) }
    }

    // Método para actualizar el TextView con el mes y año actuales
    private fun updateFooterText() {
        // Obtenemos el mes y año actuales
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR) // Año actual
        val month: Int =
            calendar.get(Calendar.MONTH) + 1 // Mes actual (es 0 basado, por lo que sumamos 1)

        // Construimos el texto actualizado
        val updatedText = "IES Ventura Rodríguez - 2º DAM - " + getMonthName(month) + " " + year

        // Actualizamos su texto
        binding.footer.text = updatedText
    }

    // Método auxiliar para obtener el nombre del mes
    private fun getMonthName(month: Int): String {
        // Array con los nombres de los meses en español
        val months = arrayOf(
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        )
        return months[month - 1] // Ajustamos porque los meses en el calendario son 0-basados
    }
}
