package com.example.cofeebreak.feature_app.presentation.Profile

import coil3.Bitmap

data class ProfileState (
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String? = null,
    val serverError: Boolean = false,
    val qrVisible: Boolean = false,
    val qrBitmap: Bitmap? = null,
    val avatarBitmap: Bitmap? = null,
    val showPicker: Boolean = false,
    val avatarUrl: String? = null,
    val loadingAvatar: Boolean = false
)