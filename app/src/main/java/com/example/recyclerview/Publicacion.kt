package com.example.recyclerview

import android.media.Image

data class Publicacion(
    val id:Int, val nombre:String, val usuario:String, val hora:Int,
    val texto:String, val imagen: Int?)