package com.example.carnetmascotas.items

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mascota(
    val nombre: String,
    val raza: String,
    val tamano: String,
    val edad: String,
    val fotoUrl: String
): Parcelable