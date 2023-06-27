package com.example.sportapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.screens.MainScreen
import com.example.sportapp.screens.SplashScreen
import com.example.sportapp.screens.WebScreen
import com.example.sportapp.viewmodel.MainViewModel


@Composable
fun Navigation(viewModel: MainViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash_screen") {

        composable("splash_screen") {
            SplashScreen(navController)
        }

        composable("main_screen") {
            MainScreen(navController, viewModel)
        }

        composable(route = "web_screen") {
            WebScreen(navController, viewModel)
        }

    }
}