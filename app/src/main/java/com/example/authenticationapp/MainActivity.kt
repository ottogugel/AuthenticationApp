package com.example.authenticationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editEmail = findViewById<EditText>(R.id.edit_email)
        val editPassword = findViewById<EditText>(R.id.edit_password)

        val botao = findViewById<Button>(R.id.button_access)
        botao.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Preencha todos os campos para continuar", Toast.LENGTH_LONG).show()
            }
        }

        val botao2 = findViewById<Button>(R.id.button_register)
        botao2.setOnClickListener {
            Toast.makeText(this, "Realize seu cadastro para acessar o aplicativo.", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

    }
}