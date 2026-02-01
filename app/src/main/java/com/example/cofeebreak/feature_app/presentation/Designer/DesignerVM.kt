package com.example.cofeebreak.feature_app.presentation.Designer

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.common.TFLite.TextGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DesignerVM @Inject constructor(
    @param:ApplicationContext private val context: Context
): ViewModel() {
    private val _state = mutableStateOf(DesignerState())
    val state: State<DesignerState> = _state

    init {
        loadDescription()
    }

    fun loadDescription() {
        viewModelScope.launch(Dispatchers.Default) {
            val generator = TextGenerator(context)
            val promtp = createPrompt(name = "Espresso Gold", country = "Ethiopia", beans = "arabica", syrup = "vanilla", milk = "cow", tastes = "marshmallow")
            val description = generator.generateText(
                promtp
            )

            withContext(Dispatchers.Main) {
                _state.value = state.value.copy(
                    description = description
                )
            }
        }
    }

    private fun createPrompt(name: String, country: String, beans: String, syrup: String, milk: String, tastes: String): String {
        return "Coffee $name, from $country, with beans $beans, includes syrup $syrup, with the addition of milk $milk, and tastes $tastes"
    }

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
            DesignerEvent.ShowDescription -> {
                _state.value = state.value.copy(
                    showDescription = !state.value.showDescription
                )
            }
        }
    }
}