package com.nt.moviesappcompose.api

import com.nt.moviesappcompose.model.Movie
import com.nt.moviesappcompose.model.SearchResponse
import com.nt.moviesappcompose.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/")
    suspend fun movieSearch(
        @Query("s")
        movieTitle: String,

        @Query("apikey")
        apiKey: String = API_KEY,

        @Query("page")
        pageNumber: Int = 1
    ) : Response<SearchResponse>

    @GET("/")
    suspend fun getMovie(
        @Query("i")
        imdbID: String,
        @Query("apikey")
        apiKey: String = API_KEY
    ) : Movie
}