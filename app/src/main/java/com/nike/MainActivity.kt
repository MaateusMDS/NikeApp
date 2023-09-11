package com.nike

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.nike.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val senha = binding.editTextPassword.text.toString()

            if (email.isEmpty()) {
                binding.editTextEmail.error = "Preencha o email"
                return@setOnClickListener
            } else if (senha.isEmpty()) {
                binding.editTextPassword.error = "Preencha a senha"
                return@setOnClickListener
            }

            startActivity(Intent(this, Home::class.java))
        }

        binding.editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.editTextEmail.text.toString()
                        .isNotEmpty() && binding.editTextPassword.text.toString().isNotEmpty()
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

        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (binding.editTextEmail.text.toString()
                        .isNotEmpty() && binding.editTextPassword.text.toString().isNotEmpty()
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
