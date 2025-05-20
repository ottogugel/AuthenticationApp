package com.example.authenticationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val editEmail = findViewById<EditText>(R.id.edit_email)
        val editPassword = findViewById<EditText>(R.id.edit_password)
        val botaoLogin = findViewById<Button>(R.id.button_access)
        val botaoCadastro = findViewById<Button>(R.id.button_register)

        botaoLogin.setOnClickListener {
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login realizado com sucesso", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, MainActivity3::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            //Toast.makeText(this, "Erro ao fazer login: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                            AuthUtils.handleFirebaseAuthError(this, task.exception)
                        }
                    }
            } else {
                Toast.makeText(this, "Preencha todos os campos para continuar", Toast.LENGTH_LONG).show()
            }
        }

        botaoCadastro.setOnClickListener {
            Toast.makeText(this, "Realize seu cadastro para acessar o aplicativo.", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
