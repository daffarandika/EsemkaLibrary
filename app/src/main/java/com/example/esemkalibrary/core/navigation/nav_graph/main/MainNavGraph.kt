package com.example.esemkalibrary.core.navigation.nav_graph.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.esemkalibrary.core.navigation.MAIN_GRAPH_ROUTE
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.core.navigation.nav_graph.main.forum.forumNavGraph
import com.example.esemkalibrary.core.navigation.nav_graph.main.cart.cartNavGraph
import com.example.esemkalibrary.core.navigation.nav_graph.main.home.homeNavGraph
import com.example.esemkalibrary.core.navigation.nav_graph.main.profile.profileNavGraph
import com.example.esemkalibrary.feature_forum.ui.ForumScreen
import com.example.esemkalibrary.feature_home.ui.HomeScreen
import com.example.esemkalibrary.feature_login.ui.LoginScreen
import com.example.esemkalibrary.feature_mycart.ui.MyCartScreen
import com.example.esemkalibrary.feature_myprofile.ui.MyProfileScreen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        route = MAIN_GRAPH_ROUTE,
    ) {
        composable(route = Screen.Main.route) {
            HomeScreen(
                navController = navController,
            )
        }
        composable(route = Screen.Forum.route) {
            ForumScreen(navController = navController)
        }
        composable(route = Screen.Cart.route) {
            MyCartScreen()
        }
        composable(route = Screen.Profile.route) {
            MyProfileScreen(navController = navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        homeNavGraph(navController = navController)
        cartNavGraph(navController = navController)
        forumNavGraph(navController = navController)
        profileNavGraph(navController = navController)
    }
}