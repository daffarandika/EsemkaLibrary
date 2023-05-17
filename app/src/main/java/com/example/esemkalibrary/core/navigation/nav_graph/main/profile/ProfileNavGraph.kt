package com.example.esemkalibrary.core.navigation.nav_graph.main.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.esemkalibrary.core.navigation.PROFILE_GRAPH_ROUTE
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.feature_borrowingdetail.ui.BorrowingDetailScreen
import com.example.esemkalibrary.feature_myprofile.ui.MyProfileScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Profile.route, route = PROFILE_GRAPH_ROUTE) {
        composable(Screen.Profile.route) {
            MyProfileScreen(navController = navController)
        }
        composable(Screen.BorrowingDetail.route) {
            BorrowingDetailScreen()
        }
    }
}