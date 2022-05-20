package com.example.tvshowcatalog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.tvshowcatalog._firebase.ClientUser
import com.example.tvshowcatalog.databinding.ActivityFormRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase




class FormRegister : AppCompatActivity() {
    private val TAG = "MESSAGE OF SYSTEM"
    private val TAGLOGIN = arrayOf("User created successfully","Cannot create a new user!")
    private lateinit var binding : ActivityFormRegisterBinding
    private val db = Firebase.firestore
    private val auth = Firebase.auth

    private val name by lazy { binding.inputNameForm }
    private val email by lazy { binding.inputEmailForm }
    private val password by lazy { binding.inputPasswordForm }
    private val buttonRegister by lazy { binding.btnRegisterForm }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        buttonRegister.setOnClickListener {
            createUserOnFirebase(it)
        }
    }
    private fun createUserOnFirebase(view:View){
        val name = name.text.toString()
        val email = email.text.toString()
        val password = password.text.toString()


        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            Snackbar.make(view,"Todos os campos devem ser preenchidos",Snackbar.LENGTH_LONG).show()
        }else {

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.i(TAG, TAGLOGIN[0])
                        saveNewUserOnCloudDataBase(name, email, password)
                    }
                }.addOnFailureListener {
                    Log.e(TAG, TAGLOGIN[1])
                }

        }

    }
    private fun saveNewUserOnCloudDataBase(name:String,email:String,password:String){
        val user = ClientUser(name,email,password)
        val clientID = auth.currentUser?.uid.toString()

        db.collection("UserMember")
            .document(clientID)
            .set(user)
            .addOnSuccessListener { task ->
                Toast.makeText(this, TAGLOGIN[0],Toast.LENGTH_LONG).show()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, TAGLOGIN[2],Toast.LENGTH_LONG).show()
            }

    }


}