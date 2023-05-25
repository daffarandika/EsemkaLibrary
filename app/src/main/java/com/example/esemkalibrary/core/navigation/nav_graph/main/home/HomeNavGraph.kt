package com.example.esemkalibrary.core.navigation.nav_graph.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.esemkalibrary.core.navigation.HOME_GRAPH_ROUTE
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.core.utils.viewModelFactory
import com.example.esemkalibrary.feature_bookdetail.ui.BookDetailScreen
import com.example.esemkalibrary.feature_home.ui.HomeScreen
import com.example.esemkalibrary.feature_home.ui.HomeViewModel

@ExperimentalFoundationApi
fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Home.route, route = HOME_GRAPH_ROUTE) {
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController, homeViewModel = viewModel(factory = viewModelFactory {
                HomeViewModel(LocalContext.current)
            }))
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