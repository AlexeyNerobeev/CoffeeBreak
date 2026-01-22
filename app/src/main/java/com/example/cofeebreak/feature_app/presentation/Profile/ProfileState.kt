package com.example.cofeebreak.feature_app.presentation.Profile

import coil3.Bitmap

data class ProfileState (
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val serverError: Boolean = false,
    val qrVisible: Boolean = false,
    val qrBitmap: Bitmap? = null
)