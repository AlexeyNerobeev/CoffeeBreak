package com.example.cofeebreak.feature_app.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@Composable
fun <T> ObserveActions(
    flow: Flow<T>,
    onAction: (T) -> Unit
) {
    val livecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(flow) {
        delay(1500)
        livecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect {
                    onAction(it)
                }
            }
        }
    }
}