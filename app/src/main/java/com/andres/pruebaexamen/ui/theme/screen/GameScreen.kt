package com.andres.pruebaexamen.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToGameOver: (String) -> Unit
) {
    var player1Card by remember { mutableStateOf<Int?>(null) }
    var player2Card by remember { mutableStateOf<Int?>(null) }
    val isGameOver = player1Card != null && player2Card != null

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Carta Alta") },
                navigationIcon = {
                    IconButton(onClick = onNavigateToHome) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Indicador y botón para Jugador 1
            Text(
                text = "Área de Juego de Jugador 1: ${player1Card?.toString() ?: "No ha robado aún"}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { player1Card = (1..13).random() },
                enabled = player1Card == null
            ) {
                Text("Jugador 1 roba carta")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Indicador y botón para Jugador 2
            Text(
                text = "Área de Juego de Jugador 2: ${player2Card?.toString() ?: "No ha robado aún"}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { player2Card = (1..13).random() },
                enabled = player2Card == null
            ) {
                Text("Jugador 2 roba carta")
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón Finalizar Juego
            Button(
                onClick = {
                    val winner = when {
                        player1Card == player2Card -> "Empate"
                        player1Card ?: 0 > player2Card ?: 0 -> "Jugador 1 Gana"
                        else -> "Jugador 2 Gana"
                    }
                    onNavigateToGameOver(winner)
                },
                enabled = isGameOver
            ) {
                Text("Terminar Partida")
            }
        }
    }
}