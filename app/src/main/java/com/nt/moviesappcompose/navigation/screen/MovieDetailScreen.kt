package com.nt.moviesappcompose.navigation.screen

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

object MovieDetailScreen: Screen {
    fun routeWithId(movieId: String): String {
        return route().replace("{movieId}", movieId)
    }

    override fun route(): String = "MOVIE_DETAIL_SCREEN/{movieId}"

    override val arguments: List<NamedNavArgument>
        get() = mutableListOf(
            navArgument("movieId") {
                type = NavType.StringType
            }
        )
}