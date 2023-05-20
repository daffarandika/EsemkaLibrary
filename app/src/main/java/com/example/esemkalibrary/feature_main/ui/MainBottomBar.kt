package com.example.esemkalibrary.feature_main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.core.graphics.scaleMatrix
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.feature_main.data.BottomNavigationItem

@Composable
fun MainBottomBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItem>,
    navController: NavHostController,
) {
    Row(modifier
        .fillMaxWidth()
        .background(SandBrown)) {
        items.forEach { item ->
            BottomTabIndicator(
                text = item.text,
                modifier = Modifier.weight((100/items.size).toFloat()),
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(route = navController.graph.route!!) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}