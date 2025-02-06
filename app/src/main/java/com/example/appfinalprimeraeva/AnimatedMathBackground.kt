package com.example.appfinalprimeraeva

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

/**
 * Custom View que dibuja un fondo animado con símbolos matemáticos en movimiento.
 * Los símbolos son seleccionados desde un array XML y se desplazan lentamente por la pantalla,
 * rebotando en los bordes. Se dibujan en color negro o blanco con una opacidad media.
 *
 * @constructor Crea la vista con un contexto y atributos opcionales.
 */
class AnimatedMathBackground(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private const val SYMBOL_COUNT = 50   // Número total de símbolos en la animación
        private const val MAX_TEXT_SIZE = 60f // Tamaño máximo de fuente para los símbolos
        private const val MIN_TEXT_SIZE = 30f // Tamaño mínimo de fuente para los símbolos
        private const val SPEED = 2f          // Velocidad máxima de movimiento de los símbolos
    }

    private val symbols = mutableListOf<MathSymbol>() // Lista que almacena los símbolos animados
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)  // Objeto Paint para dibujar los símbolos
    private var initialized = false                   // Bandera para evitar reinicialización innecesaria
    private var symbolList: List<String> = listOf()   // Lista de símbolos cargados desde XML

    init {
        loadSymbolsFromXml(context) // Carga los símbolos desde el archivo de recursos
    }

    /**
     * Dibuja los símbolos en el lienzo.
     * Se llama automáticamente en cada fotograma de la animación.
     *
     * @param canvas El lienzo en el que se dibujan los símbolos.
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Actualiza y dibuja cada símbolo en la pantalla
        symbols.forEach { symbol ->
            symbol.update(width, height) // Actualiza la posición del símbolo
            paint.color = symbol.color   // Asigna el color del símbolo al objeto Paint
            paint.textSize = symbol.size // Ajusta el tamaño del texto
            canvas.drawText(symbol.char, symbol.x, symbol.y, paint) // Dibuja el texto en el lienzo
        }

        // Solicita la redibujación de la vista en el siguiente frame
        postInvalidateOnAnimation()
    }

    /**
     * Se ejecuta cuando la vista cambia de tamaño.
     * Se usa para inicializar los símbolos solo la primera vez.
     *
     * @param w Nuevo ancho de la vista.
     * @param h Nuevo alto de la vista.
     * @param oldw Ancho anterior de la vista.
     * @param oldh Alto anterior de la vista.
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Solo inicializar los símbolos una vez
        if (!initialized) {
            symbols.clear() // Elimina cualquier símbolo previo
            repeat(SYMBOL_COUNT) {
                symbols.add(MathSymbol(w, h)) // Crea nuevos símbolos dentro de los límites de la vista
            }
            initialized = true // Marca la vista como inicializada
        }
    }

    /**
     * Carga los símbolos desde el archivo XML en res/values/symbols.xml.
     */
    private fun loadSymbolsFromXml(context: Context) {
        val typedArray: TypedArray = context.resources.obtainTypedArray(R.array.math_symbols)
        symbolList = context.resources.getStringArray(R.array.math_symbols).toList()
        typedArray.recycle()
    }

    /**
     * Clase interna que representa un símbolo matemático en movimiento.
     *
     * @property x Posición X del símbolo.
     * @property y Posición Y del símbolo.
     * @property size Tamaño de fuente del símbolo.
     * @property dx Velocidad de desplazamiento en el eje X.
     * @property dy Velocidad de desplazamiento en el eje Y.
     * @property char Carácter del símbolo matemático, seleccionado de la lista XML.
     * @property color Color del símbolo (negro o blanco con opacidad media).
     */
    private inner class MathSymbol(width: Int, height: Int) {
        var x = Random.nextInt(width).toFloat()  // Posición X aleatoria dentro del ancho de la vista
        var y = Random.nextInt(height).toFloat() // Posición Y aleatoria dentro del alto de la vista
        var size = Random.nextFloat() * (MAX_TEXT_SIZE - MIN_TEXT_SIZE) + MIN_TEXT_SIZE // Tamaño de fuente aleatorio
        var dx = (Random.nextFloat() - 0.5f) * SPEED // Velocidad en X aleatoria en un rango negativo/positivo
        var dy = (Random.nextFloat() - 0.5f) * SPEED // Velocidad en Y aleatoria en un rango negativo/positivo

        // Selecciona aleatoriamente un símbolo de la lista XML
        val char = symbolList.random()

        // Selecciona aleatoriamente entre negro o blanco
        val colorBase = if (Random.nextBoolean()) Color.BLACK else Color.WHITE
        // Asigna una opacidad media (cerca de 100-200)
        val alpha = Random.nextInt(100, 200)
        val color = Color.argb(alpha, Color.red(colorBase), Color.green(colorBase), Color.blue(colorBase))

        /**
         * Actualiza la posición del símbolo y hace que rebote en los bordes.
         *
         * @param width Ancho actual de la vista.
         * @param height Alto actual de la vista.
         */
        fun update(width: Int, height: Int) {
            x += dx // Mueve el símbolo en el eje X
            y += dy // Mueve el símbolo en el eje Y

            // Si el símbolo toca los bordes, invierte la dirección del movimiento
            if (x < 0 || x > width) dx = -dx
            if (y < size || y > height) dy = -dy
        }
    }
}
