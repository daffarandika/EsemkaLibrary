package com.example.esemkalibrary.feature_forum.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.esemkalibrary.core.components.theme.DirtBrown
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.navigation.Screen

@Composable
fun ForumScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(route = Screen.AddThread.route) },
            backgroundColor = DirtBrown,
        ) {
            Text("+")
        }
    },
        backgroundColor = SandBrown
    ) {
        LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(9) {
                ForumCard(modifier = modifier.fillMaxWidth()) { navController.navigate(Screen.ThreadDetail.route) }
            }
        }
    }
}