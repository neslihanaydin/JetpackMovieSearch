package com.nt.moviesappcompose.view.moviedetail

import androidx.lifecycle.ViewModel
import com.nt.moviesappcompose.api.RetrofitAPI
import com.nt.moviesappcompose.model.LoadingState
import com.nt.moviesappcompose.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MovieDetailViewUIState(
    var response: Movie? = null,
    var loadingState: LoadingState = LoadingState.Loading
)

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val retrofitAPI: RetrofitAPI
): ViewModel() {
    private val _uiState = MutableStateFlow(MovieDetailViewUIState())
    val uiState: StateFlow<MovieDetailViewUIState> = _uiState
    suspend fun getMovieDetail(movieId: String) {
        _uiState.update {
            it.copy(loadingState = LoadingState.Loading)
        }
        val movie = retrofitAPI.getMovie(movieId)
        _uiState.update {
            it.copy(
                response = movie,
                loadingState = LoadingState.Success
            )
        }
    }
}