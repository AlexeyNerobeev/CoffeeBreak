package com.example.cofeebreak.feature_app.presentation.Profile

import android.graphics.Bitmap
import android.net.Uri

sealed class ProfileEvent {
    data object ChangeError: ProfileEvent()
    data object QRVisibleChange: ProfileEvent()
    data class AvatarFromGallery(val uri: Uri) : ProfileEvent()
    data class AvatarFromCamera(val bitmap: Bitmap) : ProfileEvent()
    data object ChangeShowPicker: ProfileEvent()
}