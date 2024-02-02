package org.example.spartaboard.domain.comment.repository

import org.example.spartaboard.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
    fun findByBoardIdAndId(boardId: Long, commentId: Long): Comment?
}