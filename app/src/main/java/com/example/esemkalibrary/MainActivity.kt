package com.example.esemkalibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.example.esemkalibrary.feature_login.ui.LoginScreen
import com.example.esemkalibrary.ui.theme.EsemkaLibraryTheme

class MainActivity : ComponentActivity() {
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
                                    popUpTo("login")
                                }
                            },
                            onSignUpClicked = {
                                navController.navigate("signup")
                            }
                        )
                    }
                    composable("main") {
                        Text("main")
                    }
                    composable("signup") {
                        Text("signup")
                    }
                }
            }
        }
    }
}