package com.example.esemkalibrary.core.navigation.nav_graph.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.*
import androidx.navigation.compose.composable
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
        composable(
            route = "${Screen.BookDetail.route}/{bookId}",
            arguments = listOf(
                navArgument("bookId"){ type = NavType.StringType }
            )
        ) {
            BookDetailScreen(bookId = it.arguments?.getString("bookId").toString())
        }
    }
}