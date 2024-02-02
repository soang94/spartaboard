package org.example.spartaboard.domain.board.service

import org.example.spartaboard.domain.board.dto.BoardResponse
import org.example.spartaboard.domain.board.dto.CreateBoardRequest
import org.example.spartaboard.domain.board.dto.UpdateBoardRequest

interface BoardService {
    fun getBoardList(): List<BoardResponse>

    fun getBoard(boardId: Long): BoardResponse

    fun createBoard(request: CreateBoardRequest, userId: Long): BoardResponse

    fun updateBoard(boardId: Long ,request: UpdateBoardRequest, userId: Long): BoardResponse

    fun delete(boardId: Long, userId: Long)
}