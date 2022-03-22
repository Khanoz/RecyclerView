package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private  val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        //val publicaciones = ArrayList<Publicacion>();

        db.collection("publicaciones").addSnapshotListener { value, error ->
            val publicaciones = value!!.toObjects(Publicacion::class.java)

            publicaciones.forEachIndexed { index, publicacion ->
                publicacion.uid = value.documents[index].id
            }
            val adapter = MyAdapter(publicaciones);
            recyclerView.adapter = adapter
        }
    }
}