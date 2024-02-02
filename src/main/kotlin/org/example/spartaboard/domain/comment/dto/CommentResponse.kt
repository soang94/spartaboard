package org.example.spartaboard.domain.comment.dto

data class CommentResponse(
    val id: Long,
    val nickname: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String
)