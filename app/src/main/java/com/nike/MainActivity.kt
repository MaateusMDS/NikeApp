package com.nike

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.nike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registrarButton.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        val lastLogin = getSharedPreferences("com.nike.lastLogin", Context.MODE_PRIVATE)
        if (lastLogin.getString("email", "") != "") {
            val intent = Intent(this, Home::class.java)
            intent.putExtra("email", lastLogin.getString("email", ""))
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            val email = binding.editTextEmail.text.toString().lowercase()
            val senha = binding.editTextSenha.text.toString()

            if (email.isEmpty()) {
                binding.editTextEmail.error = "Preencha o email"
                return@setOnClickListener
            } else if (senha.isEmpty()) {
                binding.editTextSenha.error = "Preencha a senha"
                return@setOnClickListener
            }

            val user = getSharedPreferences("com.nike.${email}", Context.MODE_PRIVATE)

            if (user.getString("email", "") != email) {
                binding.editTextEmail.error = "Nenhuma conta possui este email cadastrado."
                return@setOnClickListener
            } else

            if (user.getString("senha", "") != senha) {
                binding.editTextSenha.error = "Senha incorreta."
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("com.nike.lastLogin", Context.MODE_PRIVATE)
            prefs.edit().apply {
                putString("email", email)
                apply()
            }

            val intent = Intent(this, Home::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

        binding.editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.editTextEmail.text.toString()
                        .isNotEmpty() && binding.editTextSenha.text.toString().isNotEmpty()
                ) {
                    binding.loginButton.backgroundTintList =
                        getColorStateList(R.color.blackSecondary)
                } else {
                    binding.loginButton.backgroundTintList =
                        getColorStateList(R.color.disableButton)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.editTextSenha.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.editTextEmail.text.toString()
                        .isNotEmpty() && binding.editTextSenha.text.toString().isNotEmpty()
                ) {
                    binding.loginButton.backgroundTintList =
                        getColorStateList(R.color.blackSecondary)
                } else {
                    binding.loginButton.backgroundTintList =
                        getColorStateList(R.color.disableButton)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })



    }
}
