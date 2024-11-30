package com.andres.pruebaexamen.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andres.pruebaexamen.ui.theme.screen.GameOverScreen
import com.andres.pruebaexamen.ui.theme.screen.GameScreen
import com.andres.pruebaexamen.ui.theme.screen.HomeScreen


@Composable
fun AppCartaNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.HOME.name) {
        composable(AppScreens.HOME.name) {
            HomeScreen {
                // Navegar al GameScreen
                navController.navigate(AppScreens.GAME.name)
            }
        }

        composable(AppScreens.GAME.name) {
            GameScreen(
                onNavigateToHome = { navController.popBackStack() },
                onNavigateToGameOver = { winner ->
                    navController.navigate("${AppScreens.GAMEOVER.name}/$winner")
                }
            )
        }

        composable(
            route = "${AppScreens.GAMEOVER.name}/{winner}",
            arguments = listOf(navArgument("winner") { type = NavType.StringType })
        ) { backStackEntry ->
            val winner = backStackEntry.arguments?.getString("winner") ?: "Empate"
            GameOverScreen(
                winner = winner,
                onRestart = {
                    // Volver al HomeScreen
                    navController.popBackStack(AppScreens.HOME.name, inclusive = false)
                }
            )
        }
    }
}