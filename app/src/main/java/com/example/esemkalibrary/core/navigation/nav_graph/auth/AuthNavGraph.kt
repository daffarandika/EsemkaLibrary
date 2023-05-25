package com.example.esemkalibrary.core.navigation.nav_graph.auth

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.esemkalibrary.core.navigation.AUTH_GRAPH_ROUTE
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.feature_home.ui.HomeViewModel
import com.example.esemkalibrary.feature_login.ui.LoginScreen
import com.example.esemkalibrary.feature_main.ui.MainScreen
import com.example.esemkalibrary.feature_signup.ui.SignUpScreen

@ExperimentalFoundationApi
@Composable
fun AuthNavGraph(navController: NavHostController, homeViewModel: HomeViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        route = AUTH_GRAPH_ROUTE
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.Main.route) {
            MainScreen()
        }
    }
}