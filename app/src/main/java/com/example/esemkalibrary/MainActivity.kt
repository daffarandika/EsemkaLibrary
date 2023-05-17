package com.example.esemkalibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.rememberNavController
import com.example.esemkalibrary.core.components.theme.EsemkaLibraryTheme
import com.example.esemkalibrary.core.navigation.nav_graph.auth.AuthNavGraph

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EsemkaLibraryTheme {
                val navController = rememberNavController()
                AuthNavGraph(navController = navController)
            }
        }
    }
}