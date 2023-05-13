package com.example.esemkalibrary.feature_main.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.feature_bookdetail.ui.BookDetailScreen
import com.example.esemkalibrary.feature_forum.ui.ForumScreen
import com.example.esemkalibrary.feature_home.ui.HomeScreen
import com.example.esemkalibrary.feature_mycart.ui.MyCartScreen
import com.example.esemkalibrary.feature_myprofile.ui.MyProfileScreen

@ExperimentalFoundationApi
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var activeIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.White) {
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                    ,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(painterResource(id = R.drawable.favicon),
                        null,
                        modifier = Modifier.size(16.dp))
                    Text("Esemka Library", color = Color.Black)
                }
            }
        },
        bottomBar = {
            MainBottomBar(
                items = listOf("Home", "Forum", "My Cart", "My Profile"),
                onActiveIndexChange = {
                    activeIndex = it
                })
        },
        backgroundColor = SandBrown
    ) {
        Box(modifier.padding(it)) {
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
}