package com.example.esemkalibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.esemkalibrary.core.components.LibraryButton
import com.example.esemkalibrary.feature_login.ui.LoginScreen
import com.example.esemkalibrary.core.components.theme.EsemkaLibraryTheme
import com.example.esemkalibrary.feature_bookdetail.ui.BookDetailScreen
import com.example.esemkalibrary.feature_main.ui.MainScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EsemkaLibraryTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login") {
                    composable("login") {
                        LoginScreen(
                            modifier = Modifier.fillMaxSize(),
                            onSuccessfulLogin = {
                                navController.navigate("main"){
                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            },
                            onSignUpClicked = {
                                navController.navigate("signup") {
                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable("main") {
                        MainScreen()
                    }
                    composable("signup") {
                        Text("signup")
                    }
                    composable("bookdetail") {
                        BookDetailScreen(id = "9")
                    }
                    composable("addthread") {
                        Text("addthread")
                    }
                    composable("threaddetail") {
                        Text("threaddetail")
                    }
                }
            }
        }
    }
}