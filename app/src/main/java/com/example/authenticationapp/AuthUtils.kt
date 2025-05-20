package com.example.authenticationapp

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.*

object AuthUtils {

    fun handleFirebaseAuthError(context: Context, exception: Exception?) {
        when (exception) {
            is FirebaseAuthInvalidUserException -> {
                showToast(context, "Email não encontrado. Verifique o e-mail.")
            }
            is FirebaseAuthInvalidCredentialsException -> {
                showToast(context, "Credenciais inválidas. Verifique o e-mail ou senha.")
            }
            is FirebaseAuthUserCollisionException -> {
                showToast(context, "Este e-mail já está cadastrado.")
            }
            else -> {
                showToast(context, "Erro: ${exception?.localizedMessage}")
            }
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
