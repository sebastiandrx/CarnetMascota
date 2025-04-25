package com.example.carnetmascotas.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun Carnet(viewModel: MascotaViewModel) {
    val mascota = viewModel.mascota

    mascota?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFE1F5FE), Color(0xFFB3E5FC))
                    )
                )
                .padding(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = cardElevation(12.dp),
                colors = cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    AsyncImage(
                        model = mascota.fotoUrl,
                        contentDescription = "Foto de la mascota",
                        modifier = Modifier
                            .size(160.dp)
                            .clip(CircleShape)
                            .border(5.dp, Color(0xFF03A9F4), CircleShape)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = mascota.nombre.uppercase(),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 24.sp,
                        color = Color(0xFF0288D1)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    InfoItem(label = "Raza", value = mascota.raza)
                    InfoItem(label = "Tama\u00f1o", value = mascota.tamano)
                    InfoItem(label = "Edad", value = mascota.edad)
                }
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$label:",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Text(
            text = value,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

