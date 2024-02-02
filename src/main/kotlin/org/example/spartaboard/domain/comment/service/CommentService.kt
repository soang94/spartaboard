package org.example.spartaboard.domain.comment.service

import org.example.spartaboard.domain.comment.dto.CommentRequest
import org.example.spartaboard.domain.comment.dto.CommentResponse

interface CommentService {
    fun getCommentList(boardId: Long): List<CommentResponse>

    fun getComment(boardId: Long, commentId: Long): CommentResponse

    fun createComment(boardId: Long, request: CommentRequest, userId: Long): CommentResponse

    fun updateComment(boardId: Long, commentId: Long, userId: Long, request: CommentRequest): CommentResponse

    fun deleteComment(boardId: Long, commentId: Long, userId: Long)
}