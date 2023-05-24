package com.example.esemkalibrary

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.navigation.compose.rememberNavController
import com.example.esemkalibrary.core.components.theme.EsemkaLibraryTheme
import com.example.esemkalibrary.core.navigation.nav_graph.auth.AuthNavGraph

class MainActivity : ComponentActivity() {
    fun restartApp() {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
        finish()
    }
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