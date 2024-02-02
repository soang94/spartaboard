package org.example.spartaboard.domain.board.service

import org.example.spartaboard.common.exception.ModelNotFoundException
import org.example.spartaboard.common.exception.UnAuthorizedAccessException
import org.example.spartaboard.domain.board.dto.BoardResponse
import org.example.spartaboard.domain.board.dto.CreateBoardRequest
import org.example.spartaboard.domain.board.dto.UpdateBoardRequest
import org.example.spartaboard.domain.board.model.Board
import org.example.spartaboard.domain.board.model.toResponse
import org.example.spartaboard.domain.board.repository.BoardRepository
import org.example.spartaboard.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository
): BoardService {
    override fun getBoardList(): List<BoardResponse> {
        return boardRepository.findAll().map { it.toResponse() }
    }

    override fun getBoard(boardId: Long): BoardResponse {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        return board.toResponse()
    }

    override fun createBoard(request: CreateBoardRequest, userId: Long): BoardResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        return boardRepository.save(
            Board(
                nickname = user.nickname,
                title = request.title,
                content = request.content,
                user = user
            )
        ).toResponse()
    }

    override fun updateBoard(boardId: Long, request: UpdateBoardRequest, userId: Long): BoardResponse {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        if (board.user.id == userId) {
            board.toUpdate(request)
        } else {
            throw UnAuthorizedAccessException(userId)
        }
        return board.toResponse()
    }

    override fun delete(boardId: Long, userId: Long) {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        if (board.user.id == userId) {
            boardRepository.delete(board)
        } else {
            throw UnAuthorizedAccessException(userId)
        }
    }

}