package com.example.carnetmascotas.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulario(
    navController: NavController,
    viewModel: MascotaViewModel,
    indexEditar: Int? = null
) {
    val isEditing = indexEditar != null
    val mascotaAEditar = indexEditar?.let { viewModel.mascotas.getOrNull(it) }
    val scrollState = rememberScrollState()
    var showError by remember { mutableStateOf(false) } // 🔧 Agregado para corregir el error

    var nombre by remember { mutableStateOf(mascotaAEditar?.nombre ?: "") }
    var raza by remember { mutableStateOf(mascotaAEditar?.raza ?: "") }
    var tamano by remember { mutableStateOf(mascotaAEditar?.tamano ?: "") }
    var edad by remember { mutableStateOf(mascotaAEditar?.edad ?: "") }
    var fotoUrl by remember { mutableStateOf(mascotaAEditar?.fotoUrl ?: "") }

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
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = if (isEditing) "Editar Mascota" else "Registrar Mascota",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0288D1),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                val fieldModifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)

                val borderColorFocused = Color(0xFF03A9F4)
                val borderColorUnfocused = Color(0xFFB0BEC5)

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") },
                    modifier = fieldModifier,
                    shape = RoundedCornerShape(14.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = borderColorFocused,
                        unfocusedBorderColor = borderColorUnfocused,
                        focusedLabelColor = borderColorFocused
                    )
                )

                OutlinedTextField(
                    value = raza,
                    onValueChange = { raza = it },
                    label = { Text("Raza") },
                    modifier = fieldModifier,
                    shape = RoundedCornerShape(14.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = borderColorFocused,
                        unfocusedBorderColor = borderColorUnfocused,
                        focusedLabelColor = borderColorFocused
                    )
                )

                OutlinedTextField(
                    value = tamano,
                    onValueChange = { tamano = it },
                    label = { Text("Tamaño") },
                    modifier = fieldModifier,
                    shape = RoundedCornerShape(14.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = borderColorFocused,
                        unfocusedBorderColor = borderColorUnfocused,
                        focusedLabelColor = borderColorFocused
                    )
                )

                OutlinedTextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Edad") },
                    modifier = fieldModifier,
                    shape = RoundedCornerShape(14.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = borderColorFocused,
                        unfocusedBorderColor = borderColorUnfocused,
                        focusedLabelColor = borderColorFocused
                    )
                )

                OutlinedTextField(
                    value = fotoUrl,
                    onValueChange = { fotoUrl = it },
                    label = { Text("URL de Foto") },
                    modifier = fieldModifier,
                    shape = RoundedCornerShape(14.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = borderColorFocused,
                        unfocusedBorderColor = borderColorUnfocused,
                        focusedLabelColor = borderColorFocused
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8A65))
                    ) {
                        Text(
                            text = "Cancelar",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }

                    Button(
                        onClick = {
                            if (nombre.isNotBlank() && raza.isNotBlank() && tamano.isNotBlank() && edad.isNotBlank() && fotoUrl.isNotBlank()) {
                                val nuevaMascota = Mascota(nombre, raza, tamano, edad, fotoUrl)
                                viewModel.agregarMascota(nuevaMascota)
                                showError = false

                                // Limpiar campos
                                nombre = ""
                                raza = ""
                                tamano = ""
                                edad = ""
                                fotoUrl = ""

                                // Ir a lista
                                navController.navigate("lista")
                            } else {
                                showError = true
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                    ) {
                        Text(
                            text = "Registrar",
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}
