package com.example.esemkalibrary.core.navigation.nav_graph.main.forum

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.esemkalibrary.core.navigation.FORUM_GRAPH_ROUTE
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.feature_forum.ui.AddThreadScreen
import com.example.esemkalibrary.feature_forum.ui.ForumScreen
import com.example.esemkalibrary.feature_forum.ui.ThreadDetailScreen

fun NavGraphBuilder.forumNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Forum.route, route = FORUM_GRAPH_ROUTE) {
        composable(route = Screen.Forum.route) {
            ForumScreen(navController = navController)
        }
        composable(route = Screen.AddThread.route) {
            AddThreadScreen(navController = navController)
        }
        composable(route = "${Screen.ThreadDetail.route}/{threadId}", arguments = listOf(
            navArgument("threadId") {type = NavType.StringType}
        )) {
            ThreadDetailScreen(threadId = it.arguments?.getString("threadId").toString())
        }
    }
}