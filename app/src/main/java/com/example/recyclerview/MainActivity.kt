package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        val publicaciones = ArrayList<Publicacion>();
        publicaciones.add(Publicacion(0, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(1, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(2, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(3, "Miguel", "@MG1", "10", "Lo que sea", R.drawable.abc, null));
        publicaciones.add(Publicacion(4, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(5, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(6, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(7, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(8, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(9, "Miguel", "@MG1", "10", "Lo que sea", R.drawable.abc, null));
        publicaciones.add(Publicacion(10, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(11, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(12, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(13, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(14, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        publicaciones.add(Publicacion(15, "Miguel", "@MG1", "10", "Lo que sea", null, null));
        val adapter = MyAdapter(publicaciones);
        recyclerView.adapter = adapter
    }
}