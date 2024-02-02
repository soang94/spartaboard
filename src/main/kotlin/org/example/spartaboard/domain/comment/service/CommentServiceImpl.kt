package org.example.spartaboard.domain.comment.service

import org.example.spartaboard.domain.comment.dto.CommentRequest
import org.example.spartaboard.domain.comment.dto.CommentResponse
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl: CommentService {
    override fun getCommentList(boardId: Long): List<CommentResponse> {
        TODO("Not yet implemented")
    }

    override fun getComment(boardId: Long, commentId: Long): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun createComment(boardId: Long, request: CommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun updateComment(boardId: Long, commentId: Long, userId: Long, request: CommentRequest): CommentResponse {
        TODO("Not yet implemented")
    }

    override fun deleteComment(boardId: Long, commentId: Long, userId: Long) {
        TODO("Not yet implemented")
    }
}