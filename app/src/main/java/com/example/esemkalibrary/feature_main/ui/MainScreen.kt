package com.example.esemkalibrary.feature_main.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.esemkalibrary.feature_forum.ui.ForumScreen
import com.example.esemkalibrary.feature_home.ui.HomeScreen
import com.example.esemkalibrary.feature_mycart.ui.MyCartScreen
import com.example.esemkalibrary.feature_myprofile.ui.MyProfileScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var activeIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = {
            MainBottomBar(
                items = listOf("Home", "Forum", "My Cart", "My Profile"),
                onActiveIndexChange = {
                    activeIndex = it
                })
        }
    ) {
        when (activeIndex) {
            0 -> {
                HomeScreen()
            }
            1 -> {
                ForumScreen()
            }
            2 -> {
                MyCartScreen()
            }
            3 -> {
                MyProfileScreen()
            }
        }
    }
}