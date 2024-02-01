package org.example.spartaboard.domain.user.dto

import java.time.LocalDateTime

data class SignupRequest(
    val email: String,
    val password: String,
    val reconfirmPassword: String,
    val name: String,
    val nickname: String,
    val info: String,
    val createdAt: LocalDateTime,
    val role: String,
)
