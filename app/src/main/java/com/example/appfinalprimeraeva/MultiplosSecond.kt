package com.example.appfinalprimeraeva

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appfinalprimeraeva.databinding.ActivityMultiplosSecondBinding

class MultiplosSecond : AppCompatActivity() {

    private lateinit var binding: ActivityMultiplosSecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMultiplosSecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.calculateButton.setOnClickListener {
            val intent = Intent(this, MultiplosFinal::class.java)
            intent.putExtra("message", "RESULTADO")
            startActivity(intent)
        }
    }
}