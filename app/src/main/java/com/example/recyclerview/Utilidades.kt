package com.example.recyclerview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

object Utilidades {
    fun mostrarError(context: Context, mensaje: String){
        AlertDialog.Builder(context).apply {
            setTitle("Error")
            setMessage(mensaje)
            setPositiveButton("Aceptar", null)
        }.show()
    }
}