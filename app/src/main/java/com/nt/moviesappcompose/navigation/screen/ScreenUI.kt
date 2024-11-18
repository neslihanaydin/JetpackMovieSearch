package com.nt.moviesappcompose.navigation.screen

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

interface ScreenUI {
    @Composable
    fun makeUI(
        navigationController: NavController,
        navArguments: Bundle?
    )
}