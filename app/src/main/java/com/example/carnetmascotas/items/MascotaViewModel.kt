package com.example.carnetmascotas.items

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class MascotaViewModel : ViewModel() {
    var mascota by mutableStateOf<Mascota?>(null)
}