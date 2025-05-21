package com.example.authenticationapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity5: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)


        val buttoncomprar = findViewById<Button>(R.id.botaocomprar)

        buttoncomprar.setOnClickListener {
            change()
        }

    }

    private fun change(){
        val linhaqtd = findViewById<EditText>(R.id.linhaquantidade)

        val itens = linhaqtd.text.toString()
        val a = 15.9
        val b = a.times(itens.toDouble())

        Toast.makeText(this, "Compra realizada com sucesso", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtra("valortotal", "R$"+b.toString())
        startActivity(intent)
    }
}