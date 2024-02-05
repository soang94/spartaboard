package org.example.spartaboard.domain.comment.service

import org.example.spartaboard.common.exception.ModelNotFoundException
import org.example.spartaboard.common.exception.UnAuthorizedAccessException
import org.example.spartaboard.domain.board.repository.BoardRepository
import org.example.spartaboard.domain.comment.dto.CommentRequest
import org.example.spartaboard.domain.comment.dto.CommentResponse
import org.example.spartaboard.domain.comment.model.Comment
import org.example.spartaboard.domain.comment.model.toResponse
import org.example.spartaboard.domain.comment.repository.CommentRepository
import org.example.spartaboard.domain.user.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository
): CommentService {
    override fun getCommentList(boardId: Long, pageable: Pageable): Page<CommentResponse> {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)

        val comments = board.comments.map { it.toResponse() }
        val startIndex = pageable.offset.toInt()
        val endIndex = (startIndex + pageable.pageSize).coerceAtMost(comments.size)
        val pageComments = comments.subList(startIndex, endIndex)

        return PageImpl(pageComments, pageable, comments.size.toLong())
    }

    override fun getComment(boardId: Long, commentId: Long): CommentResponse {
        val comment = commentRepository.findByBoardIdAndId(boardId, commentId) ?: throw ModelNotFoundException("Comment", commentId)

        return comment.toResponse()
    }

    @Transactional
    override fun createComment(boardId: Long, request: CommentRequest, userId: Long): CommentResponse {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)

        return commentRepository.save(
            Comment(
                nickname = user.nickname,
                content = request.content,
                user = user,
                board = board
            )
        ).toResponse()
//        val comment = Comment(
//            nickname = user.nickname,
//            content = request.content,
//            user = user,
//            board = board
//        )
//        board.addComment(comment)
//        boardRepository.save(board)
//
//        return comment.toResponse()
    }

    @Transactional
    override fun updateComment(boardId: Long, commentId: Long, userId: Long, request: CommentRequest): CommentResponse {
        val comment = commentRepository.findByBoardIdAndId(boardId, commentId) ?: throw ModelNotFoundException("Comment", commentId)

        if (comment.user.id == userId) {
            comment.toUpdate(request)
        } else {
            throw UnAuthorizedAccessException(userId)
        }
        return comment.toResponse()
    }

    @Transactional
    override fun deleteComment(boardId: Long, commentId: Long, userId: Long) {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        if (comment.user.id == userId) {
            board.removeComment(comment)
            commentRepository.save(comment)
        } else {
            throw UnAuthorizedAccessException(userId)
        }
    }
}