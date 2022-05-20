package com.example.tvshowcatalog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.tvshowcatalog.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private val auth = Firebase.auth

    private val email by lazy { binding.itEmailLogin }
    private val password by lazy { binding.itPasswordLogin }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.btnLogin.setOnClickListener { view ->
            userClientLogin(view)
        }
        binding.tvRegisterAccount.setOnClickListener {
            val intent = Intent(this,FormRegister::class.java)
            startActivity(intent)
        }
    }

    private fun userClientLogin(view:View){
        val email = email.text.toString()
        val password = password.text.toString()
        if(email.isEmpty() || password.isEmpty()) {
            Snackbar.make(view, "Todos os campos devem estar preenchidos", Snackbar.LENGTH_LONG).show()
        }else {

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login feito com sucesso!", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Erro ao fazer login $task", Toast.LENGTH_LONG).show()
                    }
                }

        }
    }

}