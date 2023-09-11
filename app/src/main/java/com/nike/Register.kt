package com.nike

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nike.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val botao = binding.registrarBotao
        botao.setOnClickListener {
            val nome = binding.editTextNome.text.toString()
            val email = binding.editTextEmail.text.toString().lowercase()
            val senha = binding.editTextSenha.text.toString()
            val telefone = binding.editTextTelefone.text.toString()
            val nascimento = binding.editTextNascimento.text.toString()

            if (nome.isEmpty()) {
                binding.editTextNome.error = "Preencha o nome"
                return@setOnClickListener
            } else if (nome.length < 3) {
                binding.editTextNome.error = "Preencha o nome com mais de 3 caracteres"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.editTextEmail.error = "Preencha o email"
                return@setOnClickListener
            }

            if (senha.isEmpty()) {
                binding.editTextSenha.error = "Preencha a senha"
                return@setOnClickListener
            }

            if (telefone.isEmpty()) {
                binding.editTextTelefone.error = "Preencha o telefone"
                return@setOnClickListener
            }

            if (nascimento.isEmpty()) {
                binding.editTextNascimento.error = "Preencha a data de nascimento"
                return@setOnClickListener
            }

            if (!email.matches(Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"))) {
                binding.editTextEmail.error = "Preencha o email no formato"
                return@setOnClickListener
            }

            if (senha.length < 5) {
                binding.editTextSenha.error = "Preencha a senha com mais de 5 caracteres"
                return@setOnClickListener
            }

            val emailE = getSharedPreferences("com.nike.${email}", Context.MODE_PRIVATE)
            if (emailE.getString("email", "") == email) {
                binding.editTextEmail.error = "Já existe uma conta com este email cadastrado."
                return@setOnClickListener
            }

            val prefs = getSharedPreferences("com.nike.${email}", Context.MODE_PRIVATE)
            prefs.edit().apply {
                putString("nome", binding.editTextNome.text.toString())
                putString("email", email)
                putString("senha", binding.editTextSenha.text.toString())
                apply()
            }
            val intent = Intent(this, Home::class.java)
            intent.putExtra("email", email)
            startActivity(intent)
        }

    }
}