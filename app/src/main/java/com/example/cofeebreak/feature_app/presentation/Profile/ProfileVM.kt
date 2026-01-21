package com.example.cofeebreak.feature_app.presentation.Profile

import android.graphics.Bitmap
import android.icu.number.IntegerWidth
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileVM @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val loadCurrentUserIdUseCase: LoadCurrentUserIdUseCase
): ViewModel(){
    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userId = loadCurrentUserIdUseCase.invoke().id.toString()
                val profile = profileRepository.getProfile(Profile(user_id = userId))
                val qrBitmap = generateQrCode("https://example.com")
                _state.value = state.value.copy(
                    name = profile.name,
                    phone = profile.phone,
                    email = profile.email,
                    address = profile.coffe_shop_address,
                    qrBitmap = qrBitmap
                )
            } catch (ex: Exception){
                Log.e("ProfileVM", ex.message.toString())
            }
        }
    }

    private fun generateQrCode(text: String, width: Int = 247, height: Int = 253): Bitmap {
        val bitMatrix: BitMatrix = MultiFormatWriter().encode(
            text,
            BarcodeFormat.QR_CODE,
            width,
            height
        )

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) android.graphics.Color.BLACK
                    else android.graphics.Color.WHITE
                )
            }
        }
        return bitmap
    }

    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.ChangeError -> {
                _state.value = state.value.copy(
                    error = ""
                )
            }
            ProfileEvent.QRVisibleChange -> {
                _state.value = state.value.copy(
                    qrVisible = !state.value.qrVisible
                )
            }
        }
    }
}