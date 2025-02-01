package com.example.appfinalprimeraeva
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

/**
 * Actividad principal que representa una calculadora simple.
 * Permite realizar operaciones matemáticas como suma, resta, multiplicación, y división.
 */
class Calculadora : AppCompatActivity() {

    /**
     * El TextView donde se mostrará el resultado de la operación matemática.
     */
    private lateinit var tvResultado: TextView

    /**
     * Expresión matemática actual ingresada por el usuario.
     * Se va concatenando a medida que el usuario introduce valores y operadores.
     */
    private var expression: String = ""

    /**
     * Método que se llama cuando se crea la actividad.
     * Aquí se inicializan los componentes de la interfaz de usuario y se configuran los manejadores de eventos.
     * 
     * @param savedInstanceState Si la actividad se recrea, contiene el estado anterior.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        // Inicialización del TextView donde se mostrará el resultado
        tvResultado = findViewById(R.id.tvResultado)

        // Inicialización de los botones numéricos y operadores
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn0: Button = findViewById(R.id.btn0)
        val btnDecimal: Button = findViewById(R.id.btnDecimal)

        val btnSuma: Button = findViewById(R.id.btnSuma)
        val btnResta: Button = findViewById(R.id.btnResta)
        val btnMultiplicacion: Button = findViewById(R.id.btnMultiplicacion)
        val btnDivision: Button = findViewById(R.id.btnDivision)
        val btnParentesisAbrir: Button = findViewById(R.id.btnParentesisAbrir)
        val btnParentesisCerrar: Button = findViewById(R.id.btnParentesisCerrar)

        val btnAC: Button = findViewById(R.id.btnAC)  // Botón para borrar toda la expresión
        val btnBorrar: Button = findViewById(R.id.btnBorrar)  // Botón para borrar el último carácter
        val btnIgual: Button = findViewById(R.id.btnIgual)  // Botón para evaluar la expresión
        val btnVolver: Button = findViewById(R.id.btnVolver)  // Botón para volver a la actividad anterior

        // Función para agregar un valor a la expresión y actualizar el TextView
        val addToExpression = { value: String ->
            expression += value
            tvResultado.text = expression
        }

        // Configura los botones numéricos y de operaciones
        btn7.setOnClickListener { addToExpression("7") }
        btn8.setOnClickListener { addToExpression("8") }
        btn9.setOnClickListener { addToExpression("9") }
        btn4.setOnClickListener { addToExpression("4") }
        btn5.setOnClickListener { addToExpression("5") }
        btn6.setOnClickListener { addToExpression("6") }
        btn1.setOnClickListener { addToExpression("1") }
        btn2.setOnClickListener { addToExpression("2") }
        btn3.setOnClickListener { addToExpression("3") }
        btn0.setOnClickListener { addToExpression("0") }
        btnDecimal.setOnClickListener { addToExpression(".") }

        btnSuma.setOnClickListener { addToExpression("+") }
        btnResta.setOnClickListener { addToExpression("-") }
        btnMultiplicacion.setOnClickListener { addToExpression("*") }
        btnDivision.setOnClickListener { addToExpression("/") }
        btnParentesisAbrir.setOnClickListener { addToExpression("(") }
        btnParentesisCerrar.setOnClickListener { addToExpression(")") }

        // Función que borra el último carácter de la expresión
        btnBorrar.setOnClickListener {
            if (expression.isNotEmpty()) {
                expression = expression.dropLast(1)
                tvResultado.text = expression
            }
        }

        // Función para limpiar la expresión completamente
        btnAC.setOnClickListener {
            expression = ""
            tvResultado.text = "0"
        }

        // Función que evalúa la expresión matemática cuando el usuario presiona "="
        btnIgual.setOnClickListener {
            if (expression.isNotEmpty()) {
                try {
                    // Usa la librería exp4j para construir y evaluar la expresión matemática
                    val result = ExpressionBuilder(expression).build().evaluate()
                    tvResultado.text = result.toString()  // Muestra el resultado en el TextView
                    expression = result.toString()  // Actualiza la expresión con el resultado para continuar
                } catch (e: Exception) {
                    tvResultado.text = "Error"  // Si hay un error en la evaluación, muestra un mensaje de error
                    expression = ""  // Limpia la expresión en caso de error
                }
            }
        }

        // Función que cierra la actividad y vuelve a la actividad anterior
        btnVolver.setOnClickListener {
            finish()
        }
    }
}
