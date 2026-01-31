package com.example.cofeebreak

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpScreen
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class UITests {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun allFieldsFilled_buttonBecomesActive() {
        composeRule.setContent {
            SignUpScreen(navController = rememberNavController())
        }

        composeRule.onNodeWithText("UserName").performTextInput("Alex")
        composeRule.onNodeWithText("Mobile Phone Number").performTextInput("123456")
        composeRule.onNodeWithText("Email Address").performTextInput("test@mail.com")
        composeRule.onNodeWithText("Password").performTextInput("Strong 1!")

        composeRule.onNodeWithTag("signUpButton")
            .assertIsEnabled()
    }

    @Test
    fun correctFields_clickSignUp_opensMenuScreen() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        ).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
        }

        composeRule.setContent {
            SignUpScreen(navController = navController)
        }

        composeRule.onNodeWithText("UserName").performTextInput("Alex")
        composeRule.onNodeWithText("Mobile Phone Number").performTextInput("123456")
        composeRule.onNodeWithText("Email Address").performTextInput("test@mail.com")
        composeRule.onNodeWithText("Password").performTextInput("Strong 1!")

        composeRule.onNodeWithTag("signUpButton").performClick()

        composeRule.runOnIdle {
            assertEquals(
                Navigation.StartupScreen,
                navController.currentDestination?.route
            )
        }
    }

}