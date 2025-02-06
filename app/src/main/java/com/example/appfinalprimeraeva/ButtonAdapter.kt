package com.example.appfinalprimeraeva

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appfinalprimeraeva.databinding.ListItemButtonBinding

class ButtonAdapter(
    private val buttonNames: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val binding = ListItemButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val buttonName = buttonNames[position]
        holder.bind(buttonName)
    }

    override fun getItemCount() = buttonNames.size

    inner class ButtonViewHolder(private val binding: ListItemButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(buttonName: String) {
            binding.button.text = buttonName
            binding.button.setOnClickListener { onClick(buttonName) }
        }
    }
}
