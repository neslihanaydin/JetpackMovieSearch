package com.nt.moviesappcompose.model

sealed class LoadingState {
    object Initial: LoadingState()
    object Loading: LoadingState()
    object Success: LoadingState()
    data class Error(val error: String): LoadingState()
}