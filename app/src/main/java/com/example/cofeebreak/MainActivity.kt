package com.example.cofeebreak

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cofeebreak.feature_app.presentation.Authorization.AuthorizationScreen
import com.example.cofeebreak.feature_app.presentation.Startup.StartupScreen
import com.example.cofeebreak.feature_app.presentation.Welcome.WelcomeScreen
import com.example.cofeebreak.ui.theme.CofeeBreakTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)    
        setContent {
            CofeeBreakTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Navigation.AuthorizationScreen) {
                    composable<Navigation.WelcomeScreen> {
                        WelcomeScreen()
                    }
                    composable<Navigation.StartupScreen> {
                        StartupScreen()
                    }
                    composable<Navigation.AuthorizationScreen> {
                        AuthorizationScreen(navController)
                    }
                }
            }
        }
    }
}