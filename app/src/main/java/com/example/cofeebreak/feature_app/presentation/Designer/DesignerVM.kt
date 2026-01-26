package com.example.cofeebreak.feature_app.presentation.Designer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DesignerVM @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(DesignerState())
    val state: State<DesignerState> = _state

    fun onEvent(event: DesignerEvent){
        when(event){
            is DesignerEvent.SliderChange -> {
                _state.value = state.value.copy(
                    sliderPosition = event.value
                )
            }
            DesignerEvent.SelectMilk -> {
                _state.value = state.value.copy(
                    selectMilk = !state.value.selectMilk
                )
            }
            DesignerEvent.SelectSyrup -> {
                _state.value = state.value.copy(
                    selectSyrup = !state.value.selectSyrup
                )
            }
        }
    }
}