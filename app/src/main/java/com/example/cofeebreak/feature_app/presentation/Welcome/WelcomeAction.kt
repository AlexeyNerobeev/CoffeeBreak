package com.example.cofeebreak.feature_app.presentation.Welcome

sealed interface WelcomeAction {

    data object OnSuccessLoadedSession: WelcomeAction
    data object UnsuccessLoadedSession: WelcomeAction
}