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
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cofeebreak.FakeRepository.FakeAuthRepository
import com.example.cofeebreak.FakeRepository.FakeProfileRepository
import com.example.cofeebreak.feature_app.domain.usecase.CreateProfileUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsEmailValidUseCase
import com.example.cofeebreak.feature_app.domain.usecase.IsPasswordStrongUseCase
import com.example.cofeebreak.feature_app.domain.usecase.SignUpUseCase
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpScreen
import com.example.cofeebreak.feature_app.presentation.SignUp.SignUpVM
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class UITests {
    @get:Rule
    val composeRule = createComposeRule()

    private lateinit var vm: SignUpVM

    private val authRepository = FakeAuthRepository()
    private val profileRepository = FakeProfileRepository()

    @Before
    fun setup() {
        vm = SignUpVM(
            signUpUseCase = SignUpUseCase(authRepository),
            createProfileUseCase = CreateProfileUseCase(profileRepository),
            isPasswordStrongUseCase = IsPasswordStrongUseCase(),
            isEmailValidUseCase = IsEmailValidUseCase()
        )
    }

    @Test
    fun allFieldsFilled_buttonBecomesActive() {
        composeRule.setContent {
            SignUpScreen(
                navController = rememberNavController(),
                vm = vm
            )
        }

        composeRule.onNodeWithTag("UserName").performTextInput("Alex")
        composeRule.onNodeWithTag("Mobile Phone Number").performTextInput("123456")
        composeRule.onNodeWithTag("Email Address").performTextInput("test@mail.com")
        composeRule.onNodeWithTag("Password").performTextInput("Strong 1!")

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
            SignUpScreen(navController = navController, vm)
        }

        composeRule.onNodeWithTag("UserName").performTextInput("Alex")
        composeRule.onNodeWithTag("Mobile Phone Number").performTextInput("123456")
        composeRule.onNodeWithTag("Email Address").performTextInput("test@mail.com")
        composeRule.onNodeWithTag("Password").performTextInput("Strong 1!")

        composeRule.onNodeWithTag("signUpButton").performClick()

        composeRule.runOnIdle {
            assertEquals(
                Navigation.StartupScreen,
                navController.currentDestination?.route
            )
        }
    }

}