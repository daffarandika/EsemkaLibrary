package com.example.esemkalibrary.core.navigation.nav_graph.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.esemkalibrary.core.navigation.HOME_GRAPH_ROUTE
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.feature_bookdetail.ui.BookDetailScreen
import com.example.esemkalibrary.feature_home.ui.HomeScreen

@ExperimentalFoundationApi
fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Home.route, route = HOME_GRAPH_ROUTE) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.BookDetail.route) {
            BookDetailScreen(id = "2")
        }
    }
}