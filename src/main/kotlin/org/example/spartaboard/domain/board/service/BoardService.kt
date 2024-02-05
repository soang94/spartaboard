package org.example.spartaboard.domain.board.service

import org.example.spartaboard.domain.board.dto.BoardResponse
import org.example.spartaboard.domain.board.dto.CreateBoardRequest
import org.example.spartaboard.domain.board.dto.UpdateBoardRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BoardService {
    fun getBoardList(pageable: Pageable): Page<BoardResponse>

    fun getBoard(boardId: Long): BoardResponse

    fun createBoard(request: CreateBoardRequest, userId: Long): BoardResponse

    fun updateBoard(boardId: Long ,request: UpdateBoardRequest, userId: Long): BoardResponse

    fun delete(boardId: Long, userId: Long)

    fun getRecentBoards(userId: Long): List<BoardResponse>
}