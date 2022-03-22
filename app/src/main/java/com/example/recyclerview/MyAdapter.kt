package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MyAdapter(val publicacionList: List<Publicacion>) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.publicacion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(publicacionList[position])
    }

    override fun getItemCount(): Int {
        return publicacionList.size
    }


    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bindItems(publicacion: Publicacion){
            val nombre = itemView.findViewById(R.id.nombre) as TextView
            val hora = itemView.findViewById(R.id.hora) as TextView
            val texto = itemView.findViewById(R.id.texto) as TextView
            val imagen = itemView.findViewById(R.id.imagen) as ImageView
            val formatter = SimpleDateFormat("dd/M/yyyy hh:mm")

            nombre.text = publicacion.nombre
            hora.text = formatter.format(publicacion.fecha)
            texto.text = publicacion.texto
            if(publicacion.imagen != null){
                imagen.layoutParams.height = 540
                imagen.layoutParams.width = 1080
                imagen.setImageResource(R.drawable.abc)
                imagen.requestLayout()
            }
        }
    }
}