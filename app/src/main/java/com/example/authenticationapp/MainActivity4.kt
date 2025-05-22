package com.example.authenticationapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity4 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val nomeInput = findViewById<EditText>(R.id.edit_nome)
        val emailInput = findViewById<EditText>(R.id.edit_email)
        val senhaInput = findViewById<EditText>(R.id.edit_senha)
        val btnSalvar = findViewById<Button>(R.id.button_salvar)

        val uid = auth.currentUser?.uid

        // Preencher campos com dados atuais
        if (uid != null) {
            firestore.collection("usuarios").document(uid).get()
                .addOnSuccessListener { doc ->
                    nomeInput.setText(doc.getString("nome"))
                    emailInput.setText(auth.currentUser?.email)
                }
        }

        btnSalvar.setOnClickListener {
            val novoNome = nomeInput.text.toString()
            val novoEmail = emailInput.text.toString()
            val novaSenha = senhaInput.text.toString()

            // Validação da senha
            if (novaSenha.isNotEmpty() && novaSenha.length < 6) {
                Toast.makeText(this, "A senha deve ter mais de 6 caracteres.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Impede que continue
            }

            val uid = auth.currentUser?.uid

            // Atualizar Firestore
            if (uid != null && novoNome.isNotEmpty()) {
                firestore.collection("usuarios").document(uid)
                    .update("nome", novoNome)
            }

            // Atualizar senha (se fornecida e válida)
            auth.currentUser?.let { user ->
                if (novaSenha.isNotEmpty()) {
                    user.updatePassword(novaSenha)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Senha atualizada com sucesso!", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Erro ao atualizar senha.", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }

            Toast.makeText(this, "Dados atualizados!", Toast.LENGTH_SHORT).show()
        }

        val button_backToHome = findViewById<Button>(R.id.button_backToHome)
        button_backToHome.setOnClickListener {
            finish()
        }

    }
}
