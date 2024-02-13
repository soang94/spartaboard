package org.example.spartaboard.domain.comment.dto

import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val nickname: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
//{
//    constructor() : this(0, "", "", LocalDateTime.now(), LocalDateTime.now())
//}