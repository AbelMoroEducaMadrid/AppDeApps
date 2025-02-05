package com.example.appfinalprimeraeva

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.view.animation.AnimationUtils
import com.example.appfinalprimeraeva.databinding.ActivityMultiplosFinalBinding

class MultiplosFinal : AppCompatActivity() {
    private lateinit var binding: ActivityMultiplosFinalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMultiplosFinalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_fast)
        binding.main.startAnimation(fadeInAnimation)

        val message = intent.getStringExtra("message")
        binding.messageTextView.text = message

        binding.repeatButton.setOnClickListener {
            val intent = Intent(this, MultiplosSecond::class.java)
            startActivity(intent)
        }

        binding.exitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}