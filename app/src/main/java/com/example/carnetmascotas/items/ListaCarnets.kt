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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun ListaCarnets(
    viewModel: MascotaViewModel,
    navController: NavController
) {
    val mascotas = viewModel.mascotas
    var indexToDelete by remember { mutableStateOf<Int?>(null) }

    // Mostrar alerta de confirmación al eliminar
    if (indexToDelete != null) {
        AlertDialog(
            onDismissRequest = { indexToDelete = null },
            title = { Text("Eliminar Mascota") },
            text = { Text("¿Estás seguro de que deseas eliminar este carnet?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.eliminarMascota(indexToDelete!!)
                    indexToDelete = null
                }) {
                    Text("Eliminar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { indexToDelete = null }) {
                    Text("Cancelar")
                }
            }
        )
    }

    // Lista de mascotas
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE1F5FE), Color(0xFFB3E5FC))
                )
            )
            .padding(16.dp)
    ) {
        itemsIndexed(mascotas) { index, mascota ->
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = cardElevation(8.dp),
                colors = cardColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    AsyncImage(
                        model = mascota.fotoUrl,
                        contentDescription = "Foto de la mascota",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color(0xFF03A9F4), CircleShape)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = mascota.nombre.uppercase(),
                        fontSize = 20.sp,
                        color = Color(0xFF0288D1)
                    )
                    InfoItem(label = "Raza", value = mascota.raza)
                    InfoItem(label = "Tamaño", value = mascota.tamano)
                    InfoItem(label = "Edad", value = mascota.edad)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                navController.navigate("formulario/$index")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4FC3F7))
                        ) {
                            Text("Editar")
                        }

                        Button(
                            onClick = {
                                indexToDelete = index
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8A65))
                        ) {
                            Text("Eliminar")
                        }
                        Button(
                            onClick = {
                                viewModel.seleccionarMascota(index)
                                navController.navigate("carnet")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
                        ) {
                            Text("Ver")
                        }
                    }
                }
            }
        }
    }

Spacer(modifier = Modifier.height(16.dp))
Button(
onClick = {
    navController.navigate("formulario") // Sin índice para registrar nuevo
},
modifier = Modifier.fillMaxWidth(),
colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784))
) {
    Text("Registrar nuevo", color = Color.White)
}
}

