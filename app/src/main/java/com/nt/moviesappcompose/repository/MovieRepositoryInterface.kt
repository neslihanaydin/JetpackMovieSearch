package com.nt.moviesappcompose.repository

import com.nt.moviesappcompose.model.Movie
import com.nt.moviesappcompose.model.SearchResponse
import retrofit2.Response

interface MovieRepositoryInterface {

    suspend fun searchMovie(movieTitle: String, pageNumber: Int) : Response<SearchResponse>

    suspend fun getMovieDetails(imdbID: String) : Movie
}