package com.example.esemkalibrary.core.navigation.nav_graph.main.cart

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.esemkalibrary.core.navigation.CART_GRAPH_ROUTE
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.feature_mycart.ui.MyCartScreen

fun NavGraphBuilder.cartNavGraph(navController: NavHostController) {
    navigation(startDestination = Screen.Cart.route, route = CART_GRAPH_ROUTE) {
        composable(Screen.Cart.route) {
            MyCartScreen()
        }
    }
}