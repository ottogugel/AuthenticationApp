package com.example.authenticationapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity2 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val registerUsername = findViewById<EditText>(R.id.register_user)
        val registerEmail = findViewById<EditText>(R.id.register_email)
        val registerPassword = findViewById<EditText>(R.id.register_password)
        val confirmPassword = findViewById<EditText>(R.id.confirm_password)

        val botaoCadastrar = findViewById<Button>(R.id.button_confirm_register)
        botaoCadastrar.setOnClickListener {
            val nome = registerUsername.text.toString().trim()
            val email = registerEmail.text.toString().trim()
            val senha = registerPassword.text.toString().trim()
            val confirmar = confirmPassword.text.toString().trim()

            if (nome.isNotEmpty() && email.isNotEmpty() && senha.isNotEmpty() && confirmar.isNotEmpty()) {
                if (senha == confirmar) {
                    auth.createUserWithEmailAndPassword(email, senha)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val uid = auth.currentUser?.uid
                                if (uid != null) {
                                    val userMap = hashMapOf(
                                        "nome" to nome,
                                        "email" to email
                                    )
                                    firestore.collection("usuarios").document(uid).set(userMap)
                                        .addOnSuccessListener {
                                            Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show()
                                            finish()
                                        }
                                        .addOnFailureListener { e ->
                                            Toast.makeText(this, "Erro ao salvar dados no Firestore: ${e.message}", Toast.LENGTH_LONG).show()
                                        }
                                }

                            } else {
                                Toast.makeText(this, "Erro ao cadastrar: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Senhas não coincidem", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }
        }

        val botaoVoltar = findViewById<Button>(R.id.button_back)
        botaoVoltar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
