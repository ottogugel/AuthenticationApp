package com.example.authenticationapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity3 : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val txtBoasVindas = findViewById<TextView>(R.id.text_welcome)
        val botaoLogout = findViewById<Button>(R.id.button_logout)

        val uid = auth.currentUser?.uid
        if (uid != null) {
            firestore.collection("usuarios").document(uid).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val nome = document.getString("nome")
                        txtBoasVindas.text = "Seja bem-vindo, $nome!"
                    } else {
                        txtBoasVindas.text = "Seja bem-vindo!"
                    }
                }
                .addOnFailureListener {
                    txtBoasVindas.text = "Erro ao buscar nome"
                }
        }

        val avatarImage = findViewById<ImageView>(R.id.avatarImage)

        avatarImage.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        botaoLogout.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Sessão encerrada com sucesso!", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
