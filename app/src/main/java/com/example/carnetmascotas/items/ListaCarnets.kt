package com.example.carnetmascotas.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun ListaCarnets(navController: NavController, viewModel: MascotaViewModel) {
    val mascotas = viewModel.mascotas

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE1F5FE), Color(0xFFB3E5FC))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp) // deja espacio para el botón inferior
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                itemsIndexed(mascotas) { index, mascota ->
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(mascota.fotoUrl),
                                contentDescription = "Foto de la mascota",
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .align(Alignment.CenterHorizontally),
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = mascota.nombre,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0288D1),
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Column(modifier = Modifier.fillMaxWidth()) {
                                InfoItem("Raza:", mascota.raza)
                                InfoItem("Tamaño:", mascota.tamano)
                                InfoItem("Edad:", mascota.edad)
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    onClick = {
                                        navController.navigate("formulario/${index}")
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4FC3F7))
                                ) {
                                    Text("Editar", color = Color.White)
                                }

                                Button(
                                    onClick = {
                                        viewModel.eliminarMascota(index)
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8A65))
                                ) {
                                    Text("Eliminar", color = Color.White)
                                }

                                Button(
                                    onClick = {
                                        navController.navigate("detalle/${index}")
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
                                ) {
                                    Text("Ver", color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = { navController.navigate("formulario") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
        ) {
            Text("Registrar nuevo", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
