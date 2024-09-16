package com.example.fitz.classes

data class SignInState(
    val isSuccessful: Boolean = false,
    val signInError: String? = null,
    val profilePhotoUrl: String? = null
)