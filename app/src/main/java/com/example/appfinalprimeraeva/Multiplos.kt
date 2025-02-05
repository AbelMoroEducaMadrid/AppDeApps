package com.example.appfinalprimeraeva

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import com.example.appfinalprimeraeva.databinding.ActivityMultiplosBinding

class Multiplos : AppCompatActivity() {
    private lateinit var binding: ActivityMultiplosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMultiplosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_fast)
        binding.main.startAnimation(fadeInAnimation)

        binding.buttonYes.setOnClickListener {
            val intent = Intent(this, MultiplosSecond::class.java)
            startActivity(intent)
        }

        binding.buttonNo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
