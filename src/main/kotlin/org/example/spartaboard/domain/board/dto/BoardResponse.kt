package org.example.spartaboard.domain.board.dto

import java.time.LocalDateTime

data class BoardResponse(
    val id: Long,
    val nickname: String,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
