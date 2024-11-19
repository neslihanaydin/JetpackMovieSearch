package com.nt.moviesappcompose.model

import android.content.Context
import com.nt.moviesappcompose.R

data class Movie(
    val Title: String,
    val Year: String,
    val Released: String,
    val Runtime: String,
    val Genre: String,
    val Director: String,
    val Plot: String,
    val Poster: String,
    val imdbRating: String,
    val imdbID: String
) {
    fun informationRows(context: Context) : List<Pair<String, String>> {
        return listOf(
            context.getString(R.string.movie_title) to Title,
            context.getString(R.string.movie_rating) to imdbRating,
            context.getString(R.string.movie_released) to Released,
            context.getString(R.string.movie_director) to Director,
            context.getString(R.string.movie_genre) to Genre,
            context.getString(R.string.movie_runtime) to Runtime,
            context.getString(R.string.movie_plot) to Plot
        )
    }
}
