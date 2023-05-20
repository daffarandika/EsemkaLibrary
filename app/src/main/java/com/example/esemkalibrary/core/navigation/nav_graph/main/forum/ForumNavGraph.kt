package com.example.esemkalibrary.core.navigation.nav_graph.auth.main.forum

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.esemkalibrary.core.navigation.FORUM_GRAPH_ROUTE
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.feature_forum.ui.AddThreadScreen
import com.example.esemkalibrary.feature_forum.ui.ForumScreen
import com.example.esemkalibrary.feature_forum.ui.ThreadDetailScreen

fun NavGraphBuilder.forumNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Forum.route, route = FORUM_GRAPH_ROUTE) {
        composable(Screen.Forum.route) {
            ForumScreen(navController = navController)
        }
        composable(Screen.AddThread.route) {
            AddThreadScreen(navController = navController)
        }
        composable(Screen.ThreadDetail.route) {
            ThreadDetailScreen()
        }
    }
}