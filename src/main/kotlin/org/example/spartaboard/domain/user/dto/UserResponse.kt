package org.example.spartaboard.domain.user.dto

import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val nickname: String,
    val info: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val role: String,
)
