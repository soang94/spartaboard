package org.example.spartaboard.domain.user.dto

data class LoginRequest(
    val email: String,
    val password: String,
)