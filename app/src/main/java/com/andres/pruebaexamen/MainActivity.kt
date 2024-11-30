package com.andres.pruebaexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.andres.pruebaexamen.navegation.AppCartaNavHost
import com.andres.pruebaexamen.ui.theme.PruebaExamenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebaExamenTheme {
                AppCartaNavHost()
            }
        }
    }
}


