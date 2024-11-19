package com.nt.moviesappcompose.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.nt.moviesappcompose.navigation.screen.NavGraph
import com.nt.moviesappcompose.ui.theme.MoviesAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MoviesAppComposeTheme {
                NavGraph(
                    navController
                )
            }
        }
    }
}