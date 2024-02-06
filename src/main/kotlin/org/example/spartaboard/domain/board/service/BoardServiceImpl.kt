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
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository
): BoardService {
    override fun getBoardList(pageable: Pageable): Page<BoardResponse> {
        return boardRepository.findAll(pageable).map { it.toResponse() }
    }

    override fun getBoard(boardId: Long): BoardResponse {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        return board.toResponse()
    }

    @Transactional
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

    @Transactional
    override fun updateBoard(boardId: Long, request: UpdateBoardRequest, userId: Long): BoardResponse {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        if (board.user.id == userId) {
            board.toUpdate(request)
        } else {
            throw UnAuthorizedAccessException(userId)
        }
        return board.toResponse()
    }

    @Transactional
    override fun delete(boardId: Long, userId: Long) {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        if (board.user.id == userId) {
            boardRepository.delete(board)
        } else {
            throw UnAuthorizedAccessException(userId)
        }
    }

    override fun searchBoardList(title: String): List<BoardResponse> {
        return boardRepository.searchBoardListByTitle(title).map { it.toResponse() }
    }

}