package com.nt.moviesappcompose.view.moviedetail

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.nt.moviesappcompose.navigation.screen.ScreenUI

class MovieDetailUI: ScreenUI {
    @Composable
    override fun makeUI(navigationController: NavController, navArguments: Bundle?) {
        navArguments?.getString("movieId")?.let { movieId ->
            MovieDetailView(movieId)
        }
    }
}