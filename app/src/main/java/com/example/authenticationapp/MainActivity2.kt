package com.example.authenticationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val registerUsername = findViewById<EditText>(R.id.register_user)
        val registerEmail = findViewById<EditText>(R.id.register_email)
        val registerPassword = findViewById<EditText>(R.id.register_password)
        val confirmPassword = findViewById<EditText>(R.id.confirm_password)

        val botaoCadastrar = findViewById<Button>(R.id.button_confirm_register)
        botaoCadastrar.setOnClickListener {
            val cadastroUsuario = registerUsername.text.toString().trim()
            val cadastroEmail = registerEmail.text.toString().trim()
            val cadastroSenha = registerPassword.text.toString().trim()
            val confirmarSenha = confirmPassword.text.toString().trim()

            if (cadastroUsuario.isNotEmpty() && cadastroEmail.isNotEmpty() && cadastroSenha.isNotEmpty() && confirmarSenha.isNotEmpty()) {
                Toast.makeText(this, "Cadastrado realizado com sucesso", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos para continuar", Toast.LENGTH_LONG).show()
            }
        }

        val botaoVoltar = findViewById<Button>(R.id.button_back)
        botaoVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}

