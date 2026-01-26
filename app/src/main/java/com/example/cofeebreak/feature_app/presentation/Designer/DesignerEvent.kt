package com.example.cofeebreak.feature_app.presentation.Designer

sealed class DesignerEvent {
    data class SliderChange(val value: Float): DesignerEvent()
    data object SelectMilk: DesignerEvent()
    data object SelectSyrup: DesignerEvent()
}