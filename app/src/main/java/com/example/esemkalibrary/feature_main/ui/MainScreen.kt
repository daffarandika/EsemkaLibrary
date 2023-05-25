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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.esemkalibrary.R
import com.example.esemkalibrary.core.components.theme.SandBrown
import com.example.esemkalibrary.core.navigation.Screen
import com.example.esemkalibrary.core.navigation.nav_graph.main.MainNavGraph
import com.example.esemkalibrary.feature_borrowingdetail.ui.BorrowingDetailScreen
import com.example.esemkalibrary.feature_forum.ui.AddThreadScreen
import com.example.esemkalibrary.feature_forum.ui.ForumScreen
import com.example.esemkalibrary.feature_home.ui.HomeScreen
import com.example.esemkalibrary.feature_home.ui.HomeViewModel
import com.example.esemkalibrary.feature_main.data.BottomNavigationItem
import com.example.esemkalibrary.feature_mycart.ui.MyCartScreen

@ExperimentalFoundationApi
@Composable
fun MainScreen(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {
    var activeIndex by remember {
        mutableStateOf(0)
    }
    val navController = rememberNavController()
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
                items = listOf(
                    BottomNavigationItem(text = "Home", screen = Screen.Home),
                    BottomNavigationItem(text = "Forum", screen = Screen.Forum),
                    BottomNavigationItem(text = "My Cart", screen = Screen.Cart),
                    BottomNavigationItem(text = "My Profile", screen = Screen.Profile),
                ),
                navController = navController
            )
        },
        backgroundColor = SandBrown
    ) {
        Box(modifier.padding(it)) {
            MainNavGraph(navController = navController, homeViewModel)
        }
    }
}