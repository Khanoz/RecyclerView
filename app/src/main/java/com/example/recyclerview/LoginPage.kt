package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoginPage : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        val login_button: Button = findViewById(R.id.login_btn)

        login_button.setOnClickListener {
            val email = findViewById<TextView>(R.id.login_email).text.toString()
            val password = findViewById<TextView>(R.id.login_password).text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    AlertDialog.Builder(this).apply {
                        setTitle("Error")
                        setMessage(it.message)
                        setPositiveButton("Aceptar", null)
                    }.show()
                }
        }
    }
}