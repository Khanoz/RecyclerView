package com.example.recyclerview

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MyAdapter(val publicacionList: List<Publicacion>, val cont : Context) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.publicacion, parent, false)
        return ViewHolder(view, cont)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(publicacionList[position])
    }

    override fun getItemCount(): Int {
        return publicacionList.size
    }


    class ViewHolder(itemView:View, val cont : Context):RecyclerView.ViewHolder(itemView){
        fun bindItems(publicacion: Publicacion){
            val imgBtn = itemView.findViewById<ImageView>(R.id.imageButton)
            showPopupMenu(imgBtn, publicacion.uid)

            val nombre = itemView.findViewById(R.id.nombre) as TextView
            val hora = itemView.findViewById(R.id.hora) as TextView
            val texto = itemView.findViewById(R.id.texto) as TextView
            val imagen = itemView.findViewById(R.id.imagen) as ImageView
            val formatter = SimpleDateFormat("dd/M/yyyy hh:mm")

            nombre.text = publicacion.nombre
            hora.text = formatter.format(publicacion.fecha)
            texto.text = publicacion.texto
            Log.d("uid", publicacion.uid.toString())
            if(publicacion.imagen != null){
                imagen.layoutParams.height = 540
                imagen.layoutParams.width = 1080
                imagen.setImageResource(R.drawable.abc)
                imagen.requestLayout()
            }
        }
        private fun showPopupMenu(view: View, uid: String?){
            val popupMenu = PopupMenu(itemView.context.applicationContext, view)

            popupMenu.inflate(R.menu.card_menu)
            popupMenu.setOnMenuItemClickListener{
                if (it != null) {
                    when (it.itemId){
                        R.id.actualizarPub -> {
                            val intent = Intent(cont, ManagePublicarActivity::class.java)
                            intent.putExtra("editar", true)
                            intent.putExtra("idPublicacion", uid)
                            startActivity(cont, intent, null)
                        }
                        R.id.borrarPub -> {
                            val db = FirebaseFirestore.getInstance()
                            if (uid != null) {
                                db.collection("publicaciones").document(uid).delete()
                            }
                        }
                    }
                }
                true
            }
            view.setOnLongClickListener {
                try{
                    val popup = PopupMenu::class.java.getDeclaredField("mPopup")
                    popup.isAccessible = true
                    val menu = popup.get(popupMenu)
                    menu.javaClass
                        .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                        .invoke(menu, true)
                }
                catch (e: Exception){
                    //e.printStackTrace()
                }
                finally{
                    popupMenu.show()
                }
                true
            }
        }
    }
}