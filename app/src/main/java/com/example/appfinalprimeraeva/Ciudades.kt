package com.example.appfinalprimeraeva

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button

/**
 * Actividad que muestra una lista de ciudades con sus respectivas descripciones e imágenes.
 * Permite la selección de una ciudad de la lista para realizar una acción al hacer clic.
 */
class Ciudades : AppCompatActivity(), OnItemClickListener {

    // Lista de ciudades que se mostrará en la interfaz de usuario.
    var miLista: ListView? = null

    // Datos de las provincias (nombres de ciudades)
    private val provincias = arrayOf(
        "Ciudad Real", "Toledo", "Guadalajara",
        "Cuenca", "Albacete", "Talavera"
    )

    // Descripciones de las provincias
    private val descripciones = arrayOf(
        "Qué gran ciudad para tapas", "La ciudad imperial", "Bonita ciudad de compras",
        "Curiosas casas colgantes", "El nueva york de la mancha", "Agua y sol"
    )

    // Imágenes asociadas a cada provincia
    private val imagenes = intArrayOf(
        R.drawable.ciudadreal, R.drawable.toledo, R.drawable.guadalajara,
        R.drawable.cuenca, R.drawable.albacete, R.drawable.talavera
    )

    /**
     * Método llamado cuando se crea la actividad.
     * Configura la lista de ciudades y su adaptador, y establece los eventos de interacción.
     *
     * @param savedInstanceState El estado guardado de la actividad, si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        val ciudades = ArrayList<Ciudad>()
        
        // Llena la lista de ciudades con los datos
        for (i in provincias.indices) {
            val c: Ciudad = Ciudad(
                provincias[i], descripciones[i], imagenes[i]
            )
            ciudades.add(c)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        // Obtiene el ListView de la interfaz
        miLista = findViewById<ListView>(R.id.miLista)

        // Crea el adaptador personalizado y lo asigna al ListView
        val adapter: MiAdaptadorPersonalizado =
            MiAdaptadorPersonalizado(this, R.layout.mi_fila_personalizada, ciudades)

        // Enlaza el adaptador con el ListView y configura el listener para los clics
        miLista?.adapter = adapter
        miLista?.onItemClickListener = this

        // Configura el botón "Volver" para regresar a la actividad principal
        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * Este método se llama cuando un ítem en la lista es clickeado.
     * Se puede implementar alguna acción al seleccionar un elemento.
     *
     * @param parent El `AdapterView` que contiene el ítem seleccionado.
     * @param view La vista del ítem seleccionado.
     * @param position La posición del ítem seleccionado.
     * @param id El ID de la fila seleccionada.
     */
    override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        // Acción al seleccionar un elemento (por ahora vacío)
    }

    /**
     * Adaptador personalizado para mostrar una lista de objetos `Ciudad` en el `ListView`.
     * Cada fila muestra el nombre, la descripción y la imagen de una ciudad.
     */
    inner class MiAdaptadorPersonalizado(
        context: Context,
        private val mResource: Int,
        objects: List<Ciudad>
    ) : ArrayAdapter<Ciudad?>(context, mResource, objects) {

        // Lista de ciudades para ser utilizadas en las filas
        private val misCiudades = objects as ArrayList<Ciudad>

        /**
         * Este método es necesario si el adaptador se utiliza con un Spinner,
         * pero aquí se reutiliza para obtener las vistas del `ListView`.
         *
         * @param position La posición del ítem en la lista.
         * @param convertView La vista reciclada de la fila (si existe).
         * @param parent El contenedor padre que aloja la vista.
         * @return La vista de la fila para el ítem en la posición dada.
         */
        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            return crearFila(position, convertView, parent)
        }

        /**
         * Este método devuelve la vista correspondiente a cada ítem de la lista.
         *
         * @param position La posición del ítem en la lista.
         * @param convertView La vista reciclada de la fila (si existe).
         * @param parent El contenedor padre que aloja la vista.
         * @return La vista de la fila para el ítem en la posición dada.
         */
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            return crearFila(position, convertView, parent)
        }

        /**
         * Crea la vista de una fila, inflando la plantilla correspondiente con la información de la ciudad.
         *
         * @param position La posición del ítem en la lista.
         * @param convertView La vista reciclada de la fila (si existe).
         * @param parent El contenedor padre que aloja la vista.
         * @return La vista completa de la fila que se mostrará en el `ListView`.
         */
        private fun crearFila(position: Int, convertView: View?, parent: ViewGroup): View {
            // Infla la fila personalizada para cada ciudad
            val miInflador = layoutInflater
            val miFila = miInflador.inflate(mResource, parent, false)

            // Encuentra las vistas dentro de la fila
            val txtDescripcion = miFila.findViewById<TextView>(R.id.txtDescripcion)
            val txtCiudad = miFila.findViewById<TextView>(R.id.txtCiudad)
            val imgCiudad = miFila.findViewById<ImageView>(R.id.imgCiudad)

            // Asigna los valores correspondientes a cada vista
            txtDescripcion.text = misCiudades[position].descripcion
            txtCiudad.text = misCiudades[position].nombre
            imgCiudad.setImageResource(misCiudades[position].imagen)

            // Log de depuración
            Log.d("RDT", "creada la fila $position")

            return miFila
        }
    }

    /**
     * Clase que representa una ciudad con un nombre, descripción y una imagen asociada.
     *
     * @property nombre El nombre de la ciudad.
     * @property descripcion Una breve descripción de la ciudad.
     * @property imagen El recurso de imagen correspondiente a la ciudad.
     */
    inner class Ciudad(var nombre: String, var descripcion: String, var imagen: Int)
}
