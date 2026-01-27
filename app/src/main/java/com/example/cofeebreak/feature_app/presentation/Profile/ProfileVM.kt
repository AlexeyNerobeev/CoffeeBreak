package com.example.cofeebreak.feature_app.presentation.Profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cofeebreak.feature_app.domain.model.Profile
import com.example.cofeebreak.feature_app.domain.repository.ProfileRepository
import com.example.cofeebreak.feature_app.domain.usecase.LoadCurrentUserIdUseCase
import com.example.cofeebreak.feature_app.domain.usecase.UpdateAvatarUrlUseCase
import com.example.cofeebreak.feature_app.domain.usecase.UploadAvatarUseCase
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import javax.inject.Inject


@HiltViewModel
class ProfileVM @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val loadCurrentUserIdUseCase: LoadCurrentUserIdUseCase,
    @param:ApplicationContext private val appContext: Context,
    private val uploadAvatarUseCase: UploadAvatarUseCase,
    private val updateAvatarUrlUseCase: UpdateAvatarUrlUseCase
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
                    address = profile.coffee_shop_address,
                    avatarUrl = profile.avatar_url,
                    qrBitmap = qrBitmap
                )
            } catch (ex: Exception){
                _state.value = state.value.copy(
                    serverError = true
                )
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

    private fun bitmapToBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, stream)
        return stream.toByteArray()
    }

    private fun uploadAvatar(bitmap: Bitmap) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.value = state.value.copy(
                    loadingAvatar = true
                )

                val userId = loadCurrentUserIdUseCase().id
                val bytes = bitmapToBytes(bitmap)

                val url = uploadAvatarUseCase.invoke(userId.toString(), bytes)
                updateAvatarUrlUseCase.invoke(userId.toString(), url)

                _state.value = state.value.copy(
                    avatarBitmap = bitmap,
                    avatarUrl = url,
                    loadingAvatar = false
                )

            } catch (ex: Exception) {
                Log.e("supabase", ex.message.toString())
                _state.value = state.value.copy(
                    serverError = true,
                    loadingAvatar = false
                )
            }
        }
    }


    fun onEvent(event: ProfileEvent){
        when(event){
            ProfileEvent.ChangeError -> {
                _state.value = state.value.copy(
                    serverError = false
                )
            }
            ProfileEvent.QRVisibleChange -> {
                _state.value = state.value.copy(
                    qrVisible = !state.value.qrVisible
                )
            }
            is ProfileEvent.AvatarFromCamera -> {
                uploadAvatar(event.bitmap)
            }

            is ProfileEvent.AvatarFromGallery -> {
                viewModelScope.launch {
                    val bitmap = withContext(Dispatchers.IO) {
                        val stream =
                            appContext.contentResolver.openInputStream(event.uri)
                        BitmapFactory.decodeStream(stream)
                    }
                    uploadAvatar(bitmap)
                }
            }
            ProfileEvent.ChangeShowPicker -> {
                _state.value = state.value.copy(
                    showPicker = !state.value.showPicker
                )
            }
        }
    }
}