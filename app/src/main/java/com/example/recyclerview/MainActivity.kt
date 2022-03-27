package com.example.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private  val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        db.collection("publicaciones").addSnapshotListener { value, error ->
            val publicaciones = value!!.toObjects(Publicacion::class.java)

            publicaciones.forEachIndexed { index, publicacion ->
                publicacion.uid = value.documents[index].id
            }
            val adapter = MyAdapter(publicaciones, this)
            recyclerView.adapter = adapter
        }

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val intent = Intent(this, ManagePublicarActivity::class.java)
            startActivity(intent)
        }
    }
    companion object fun UpdatePost(){
        Log.d("aaaa", "bbbbbb")
    }
}