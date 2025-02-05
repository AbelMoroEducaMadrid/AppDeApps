package com.example.appfinalprimeraeva

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.random.Random

/**
 * Custom View que dibuja un fondo animado con puntos en movimiento.
 * Los puntos se generan con un color aleatorio en escala de grises y
 * se desplazan lentamente por la pantalla, rebotando en los bordes.
 *
 * @constructor Crea la vista con un contexto y atributos opcionales.
 */
class AnimatedDotsBackground(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    companion object {
        private const val DOT_COUNT = 50  // Número total de puntos en la animación
        private const val MAX_RADIUS = 15 // Tamaño máximo del radio de un punto
        private const val MIN_RADIUS = 5  // Tamaño mínimo del radio de un punto
        private const val SPEED = 2       // Velocidad máxima de movimiento de los puntos
    }

    private val dots = mutableListOf<Dot>() // Lista que almacena los puntos animados
    private val paint = Paint()             // Objeto Paint para dibujar los puntos
    private var initialized = false         // Bandera para evitar reinicializar los puntos innecesariamente

    /**
     * Dibuja los puntos en el lienzo.
     * Se llama automáticamente en cada fotograma de la animación.
     *
     * @param canvas El lienzo en el que se dibujan los puntos.
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Actualiza y dibuja cada punto en la pantalla
        dots.forEach { dot ->
            dot.update(width, height)  // Actualiza la posición del punto
            paint.color = dot.color    // Asigna el color del punto al objeto Paint
            canvas.drawCircle(dot.x, dot.y, dot.radius, paint) // Dibuja el círculo
        }

        // Solicita la redibujación de la vista en el siguiente frame
        postInvalidateOnAnimation()
    }

    /**
     * Se ejecuta cuando la vista cambia de tamaño.
     * Se usa para inicializar los puntos solo la primera vez.
     *
     * @param w Nuevo ancho de la vista.
     * @param h Nuevo alto de la vista.
     * @param oldw Ancho anterior de la vista.
     * @param oldh Alto anterior de la vista.
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Solo inicializar los puntos una vez
        if (!initialized) {
            dots.clear() // Elimina cualquier punto previo
            repeat(DOT_COUNT) {
                dots.add(Dot(w, h)) // Crea nuevos puntos dentro de los límites de la vista
            }
            initialized = true // Marca la vista como inicializada
        }
    }

    /**
     * Clase interna que representa un punto animado en la pantalla.
     *
     * @property x Posición X del punto.
     * @property y Posición Y del punto.
     * @property radius Radio del punto.
     * @property dx Velocidad de desplazamiento en el eje X.
     * @property dy Velocidad de desplazamiento en el eje Y.
     * @property color Color del punto en escala de grises.
     */
    private inner class Dot(width: Int, height: Int) {
        var x = Random.nextInt(width).toFloat()  // Posición X aleatoria dentro del ancho de la vista
        var y = Random.nextInt(height).toFloat() // Posición Y aleatoria dentro del alto de la vista
        var radius = Random.nextInt(MIN_RADIUS, MAX_RADIUS).toFloat() // Radio aleatorio dentro del rango
        var dx = (Random.nextFloat() - 0.5f) * SPEED // Velocidad en X aleatoria en un rango negativo/positivo
        var dy = (Random.nextFloat() - 0.5f) * SPEED // Velocidad en Y aleatoria en un rango negativo/positivo
        val grayValue = Random.nextInt(256) // Valor de gris aleatorio (0-255)
        val color = Color.rgb(grayValue, grayValue, grayValue) // Color en escala de grises

        /**
         * Actualiza la posición del punto y hace que rebote en los bordes.
         *
         * @param width Ancho actual de la vista.
         * @param height Alto actual de la vista.
         */
        fun update(width: Int, height: Int) {
            x += dx // Mueve el punto en el eje X
            y += dy // Mueve el punto en el eje Y

            // Si el punto toca los bordes, invierte la dirección del movimiento
            if (x < 0 || x > width) dx = -dx
            if (y < 0 || y > height) dy = -dy
        }
    }
}
