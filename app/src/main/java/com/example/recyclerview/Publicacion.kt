package com.example.recyclerview

import com.google.firebase.firestore.Exclude
import java.util.*
import kotlin.collections.ArrayList

data class Publicacion(
                       val nombre:String?,
                       val fecha:Date?,
                       val texto:String?,
                       val likes: ArrayList<String>? = arrayListOf(),
                       val imagen: Int? = null,
                       val video: Int? = null){
    @Exclude
    @set:Exclude
    @get:Exclude
    var uid: String? = null
    constructor(): this(null,null,null,null,null,null)
}