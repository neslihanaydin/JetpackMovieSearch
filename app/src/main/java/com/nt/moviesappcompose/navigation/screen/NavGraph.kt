package com.nt.moviesappcompose.navigation.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nt.moviesappcompose.view.movie.MovieUI
import com.nt.moviesappcompose.view.moviedetail.MovieDetailUI

val Destinations: Map<Screen, ScreenUI> = mapOf(
    MovieScreen to MovieUI(),
    MovieDetailScreen to MovieDetailUI()
)

@Composable
fun NavGraph(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = MovieScreen.route()
) {
    NavHost(
        navHostController,
        startDestination
    ) {
        addDestionations(
            Destinations,
            navHostController
        )
    }
}

fun NavGraphBuilder.addDestionations(
    destionations: Map<Screen, ScreenUI>,
    navController: NavController
) {
    destionations.forEach { entry ->
        val destionation = entry.key
        composable(destionation.route(), destionation.arguments) {
            entry.value.makeUI(
                navController,
                it.arguments
            )
        }
    }
}