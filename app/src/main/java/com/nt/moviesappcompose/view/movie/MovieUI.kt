package com.nt.moviesappcompose.view.movie

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.nt.moviesappcompose.navigation.screen.MovieDetailScreen
import com.nt.moviesappcompose.navigation.screen.ScreenUI

class MovieUI: ScreenUI {
    @Composable
    override fun makeUI(
        navigationController: NavController,
        navArguments: Bundle?
    ) {
        MovieView(onClick = { movieId ->
            navigationController.navigate(MovieDetailScreen.routeWithId(movieId))
        })
    }
}