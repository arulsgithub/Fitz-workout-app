package com.example.fitz


data class SignInResult(
    val data: userData?,
    val errorMessage: String?
)

data class userData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?,
    val email: String?
)