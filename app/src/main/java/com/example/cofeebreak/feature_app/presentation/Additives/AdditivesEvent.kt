package com.example.cofeebreak.feature_app.presentation.Additives

sealed class AdditivesEvent {
    data object ChangeError: AdditivesEvent()
}