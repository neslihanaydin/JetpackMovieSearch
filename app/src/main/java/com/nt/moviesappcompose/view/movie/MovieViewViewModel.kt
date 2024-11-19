package com.nt.moviesappcompose.view.movie

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nt.moviesappcompose.api.RetrofitAPI
import com.nt.moviesappcompose.model.LoadingState
import com.nt.moviesappcompose.model.Movie
import com.nt.moviesappcompose.model.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieViewUIState(
    var response: SearchResponse? = null,
    var loadingState: LoadingState = LoadingState.Initial,
    val searchText: MutableState<String> = mutableStateOf(""),
    val previewMovies: MutableList<Movie> = mutableListOf()
)

@HiltViewModel
class MovieViewViewModel @Inject constructor(
    private val retrofitAPI: RetrofitAPI
): ViewModel() {
    private val _uiState = MutableStateFlow(MovieViewUIState())
    val uiState: StateFlow<MovieViewUIState> = _uiState.asStateFlow()

    var job: Job? = null

    init {
        viewModelScope.launch {
            createPreviewMovies()
        }
    }

    private suspend fun createPreviewMovies() {
        val movieIdArray = arrayOf("tt1201607", "tt0120737", "tt0903624", "tt1375666", "tt0099785")
        movieIdArray.forEach { movieId ->
            getMovieById(movieId)
        }
    }
    private suspend fun getMovieById(movieId: String) {
        _uiState.update {
            it.copy(
                loadingState = LoadingState.Loading
            )
        }
        val movie = retrofitAPI.getMovie(movieId)
        _uiState.value.previewMovies.add(movie)
        _uiState.update {
            it.copy(
                loadingState = LoadingState.Initial
            )
        }
    }
    fun updateSearchText(searchText: String) {
        _uiState.value.searchText.value = searchText
        job?.cancel()
        if (searchText.isEmpty()) {
            _uiState.update {
                it.copy(
                    loadingState = LoadingState.Initial
                )
            }
        } else {
            job = viewModelScope.launch {
                delay(1000)
                searchMovie(searchText)
            }
        }
    }
    private suspend fun searchMovie(title: String) {
        _uiState.update {
            it.copy(
                loadingState = LoadingState.Loading
            )
        }
        val moviesResponse = retrofitAPI.movieSearch(movieTitle = title)
        if (moviesResponse.isSuccessful) {
            Log.d("MovieViewViewModel", moviesResponse.body().toString())
            if (moviesResponse.body()?.Search.isNullOrEmpty()){
                _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Error(error = "Movie Not Found.")
                    )
                }
            } else {
                _uiState.update {
                    it.copy(
                        loadingState = LoadingState.Success,
                        response = moviesResponse.body()
                    )
                }
            }
        } else {
            _uiState.update {
                it.copy(
                    loadingState = LoadingState.Error(error = moviesResponse.errorBody().toString())
                )
            }
        }
    }
}

