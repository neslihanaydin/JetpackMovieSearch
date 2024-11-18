package com.nt.moviesappcompose.model

sealed class LoadingState {
    object Initial: LoadingState()
    object Success: LoadingState()
    object Loading: LoadingState()
    data class Error(val error: String): LoadingState()
}