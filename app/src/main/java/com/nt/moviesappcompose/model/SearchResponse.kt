package com.nt.moviesappcompose.model

data class SearchResponse(
    val Search: MutableList<Movie>,
    val totalResults: String,
    val Response: String
    )