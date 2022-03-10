package com.example.recyclerview

import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(val publicacionList: ArrayList<Publicacion>) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){


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
            val id = itemView.findViewById(R.id.id) as TextView
            val nombre = itemView.findViewById(R.id.nombre) as TextView
            val usuario = itemView.findViewById(R.id.usuario) as TextView
            val hora = itemView.findViewById(R.id.hora) as TextView
            val texto = itemView.findViewById(R.id.texto) as TextView
            val img = itemView.findViewById(R.id.imageview) as ImageView

            id.text = publicacion.id.toString()
            nombre.text = publicacion.nombre
            usuario.text = publicacion.usuario
            hora.text = publicacion.hora.toString()
            texto.text = publicacion.texto
            if(publicacion.imagen != null){
                img.layoutParams.height = 413
                img.layoutParams.width = 210
                img.requestLayout()
                img.setImageResource(publicacion.imagen)
            }
        }
    }
}