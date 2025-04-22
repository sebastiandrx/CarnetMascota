package com.example.carnetmascotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.carnetmascotas.items.Formulario
import com.example.carnetmascotas.items.Carnet
import com.example.carnetmascotas.items.MascotaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: MascotaViewModel by viewModels()

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "formulario") {
                composable("formulario") {
                    Formulario(navController = navController, viewModel = viewModel)
                }
                composable("carnet") {
                    Carnet(viewModel = viewModel)
                }
            }
        }
    }
}
