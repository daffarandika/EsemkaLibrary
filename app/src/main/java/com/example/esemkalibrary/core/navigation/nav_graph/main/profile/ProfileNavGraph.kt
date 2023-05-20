package com.example.esemkalibrary.core.navigation.nav_graph.main.profile

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.esemkalibrary.core.navigation.PROFILE_GRAPH_ROUTE
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.feature_borrowingdetail.ui.BorrowingDetailScreen
import com.example.esemkalibrary.feature_myprofile.ui.MyProfileScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Profile.route, route = PROFILE_GRAPH_ROUTE) {
        composable(route = Screen.Profile.route) {
            MyProfileScreen(navController = navController)
        }
        composable(
            route = "${Screen.BorrowingDetail.route}/{borrowingId}",
            arguments = listOf(navArgument("borrowingId") {
                type = NavType.StringType
            })
        ) {
            BorrowingDetailScreen(borrowingId = it.arguments?.getString("borrowingId").toString())
        }
    }
}