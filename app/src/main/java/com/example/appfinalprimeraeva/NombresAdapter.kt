package com.example.appfinalprimeraeva

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter personalizado para mostrar una lista de nombres en un RecyclerView.
 *
 * @param nombres Array de nombres que se mostrará en la lista.
 */
class NombresAdapter(private val nombres: Array<String>) : RecyclerView.Adapter<NombresAdapter.NombreViewHolder>() {

    /**
     * Crea una nueva vista de item en el RecyclerView.
     *
     * @param parent El ViewGroup padre en el que se va a insertar el item.
     * @param viewType El tipo de vista, usado si el RecyclerView tiene múltiples tipos de elementos.
     * @return Un nuevo ViewHolder que contiene la vista inflada.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NombreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return NombreViewHolder(view)
    }

    /**
     * Vincula los datos al ViewHolder en una posición específica.
     *
     * @param holder El ViewHolder al que se deben vincular los datos.
     * @param position La posición en el conjunto de datos.
     */
    override fun onBindViewHolder(holder: NombreViewHolder, position: Int) {
        holder.bind(nombres[position])
    }

    /**
     * Devuelve el número total de elementos en la lista de nombres.
     *
     * @return El tamaño del array de nombres.
     */
    override fun getItemCount(): Int = nombres.size

    /**
     * ViewHolder que representa un item individual de la lista de nombres.
     *
     * @param itemView La vista del item que contiene un TextView para mostrar el nombre.
     */
    class NombreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(android.R.id.text1)

        /**
         * Asocia un nombre al TextView dentro del ViewHolder.
         *
         * @param nombre El nombre que se mostrará en el TextView.
         */
        fun bind(nombre: String) {
            textView.text = nombre
        }
    }
}
