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

            // Atualizar Firestore
            if (uid != null && novoNome.isNotEmpty()) {
                firestore.collection("usuarios").document(uid)
                    .update("nome", novoNome)
            }

            // Atualizar e-mail e senha (se fornecidos)
            auth.currentUser?.let { user ->
                if (novoEmail.isNotEmpty() && novoEmail != user.email) {
                    user.updateEmail(novoEmail)
                }
                if (novaSenha.isNotEmpty()) {
                    user.updatePassword(novaSenha)
                }
            }

            // Voltar para a tela inicial.
            val buttonBackToHome = findViewById<Button>(R.id.button_backToHome)
            buttonBackToHome.setOnClickListener {
                finish()
            }

            Toast.makeText(this, "Dados atualizados!", Toast.LENGTH_SHORT).show()
        }
    }
}
