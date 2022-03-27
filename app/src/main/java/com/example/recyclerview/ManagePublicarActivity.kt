package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class ManagePublicarActivity : AppCompatActivity() {
    private  val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private lateinit var nombre: String
    private lateinit var texto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_publicar)
        getNombre()
        val editar = intent.extras?.getBoolean("editar", false)

        if (editar == true)
            actualizarPublicacion()
        else
            crearPublicacion()
    }
    private fun getNombre(){
        val uid = auth.currentUser!!.uid

        db.collection("usuarios").document(uid).get()
            .addOnSuccessListener {
                nombre = it.get("nombre").toString()
                findViewById<TextView>(R.id.nombre).text = nombre
            }
            .addOnFailureListener {
                Utilidades.mostrarError(this, it.message.toString())
                finish()
            }
    }
    private fun crearPublicacion(){
        val publicarBtn: Button = findViewById(R.id.publicar_btn)

        publicarBtn.setOnClickListener {

            texto = findViewById<EditText>(R.id.publicacion).text.toString()
            val fecha = Date()
            val publicacion = Publicacion(nombre, fecha, texto)

            db.collection("publicaciones").add(publicacion)
                .addOnSuccessListener {
                    finish()
                }
                .addOnFailureListener {
                    Utilidades.mostrarError(this, it.message.toString())
                }
        }
    }
    private fun actualizarPublicacion(){
        val publicarBtn: Button = findViewById(R.id.publicar_btn)
        val editText = findViewById<EditText>(R.id.publicacion)
        val idPublicacion = intent.extras!!.getString("idPublicacion", "0")

        db.collection("publicaciones").document(idPublicacion).get()
            .addOnSuccessListener {
                editText.text = SpannableStringBuilder(it.get("texto").toString())
                findViewById<TextView>(R.id.nombre).text = it.get("nombre").toString()
            }
            .addOnFailureListener {
                Utilidades.mostrarError(this, it.message.toString())
            }

        publicarBtn.setOnClickListener {
            val nuevoTexto = editText.text.toString()

            db.collection("publicaciones").document(idPublicacion)
                .update("texto", nuevoTexto, "ultimaEdicion", Date())
                .addOnSuccessListener {
                    finish()
                }
                .addOnFailureListener {
                    Utilidades.mostrarError(this, it.message.toString())
                }
        }
    }
}