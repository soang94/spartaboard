package org.example.spartaboard.domain.comment.service

import org.example.spartaboard.domain.comment.dto.CommentRequest
import org.example.spartaboard.domain.comment.dto.CommentResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CommentService {
    fun getCommentList(boardId: Long, pageable: Pageable): Page<CommentResponse>

    fun getComment(boardId: Long, commentId: Long): CommentResponse

    fun createComment(boardId: Long, request: CommentRequest, userId: Long): CommentResponse

    fun updateComment(boardId: Long, commentId: Long, userId: Long, request: CommentRequest): CommentResponse

    fun deleteComment(boardId: Long, commentId: Long, userId: Long)
}