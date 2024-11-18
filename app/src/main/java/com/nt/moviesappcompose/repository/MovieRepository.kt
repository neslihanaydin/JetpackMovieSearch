package com.nt.moviesappcompose.repository

import com.nt.moviesappcompose.api.RetrofitAPI
import com.nt.moviesappcompose.model.Movie
import com.nt.moviesappcompose.model.SearchResponse
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val retrofitAPI: RetrofitAPI
) : MovieRepositoryInterface {
    override suspend fun searchMovie(
        movieTitle: String,
        pageNumber: Int
    ): Response<SearchResponse> {
        return retrofitAPI.movieSearch(movieTitle = movieTitle, pageNumber = pageNumber)
    }

    override suspend fun getMovieDetails(imdbID: String): Movie {
        return retrofitAPI.getMovie(imdbID = imdbID)
    }
}