package com.nike

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nike.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")

        val prefs = getSharedPreferences("com.nike.lastLogin", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("email", email)
            apply()
        }

        val user = getSharedPreferences("com.nike.${email}", Context.MODE_PRIVATE)
        val nome = user.getString("nome", "valor se nulo")
        binding.textoInicio.text = "Olá, $nome"

        binding.saidaButton.setOnClickListener(){
            deleteSharedPreferences("com.nike.lastLogin")
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}