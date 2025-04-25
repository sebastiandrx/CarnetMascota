package com.example.carnetmascotas.items

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class MascotaViewModel : ViewModel() {
    private val _mascotas = mutableStateListOf<Mascota>()
    val mascotas: List<Mascota> get() = _mascotas

    var mascota: Mascota? = null
        private set

    fun agregarMascota(mascota: Mascota) {
        _mascotas.add(mascota)
    }

    fun actualizarMascota(index: Int, mascota: Mascota) {
        if (index in _mascotas.indices) {
            _mascotas[index] = mascota
        }
    }

    fun eliminarMascota(index: Int) {
        if (index in _mascotas.indices) {
            _mascotas.removeAt(index)
        }
    }

    fun seleccionarMascota(index: Int) {
        if (index in _mascotas.indices) {
            mascota = _mascotas[index]
        }
    }
}