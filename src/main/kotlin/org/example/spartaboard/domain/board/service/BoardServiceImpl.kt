package org.example.spartaboard.domain.board.service

import org.example.spartaboard.domain.board.dto.BoardResponse
import org.example.spartaboard.domain.board.dto.CreateBoardRequest
import org.example.spartaboard.domain.board.dto.UpdateBoardRequest
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl: BoardService {
    override fun getBoardList(): List<BoardResponse> {
        TODO("Not yet implemented")
    }

    override fun getBoard(boardId: Long): BoardResponse {
        TODO("Not yet implemented")
    }

    override fun createBoard(request: CreateBoardRequest): BoardResponse {
        TODO("Not yet implemented")
    }

    override fun updateBoard(request: UpdateBoardRequest, userId: Long): BoardResponse {
        TODO("Not yet implemented")
    }

    override fun delete(boardId: Long, userId: Long) {
        TODO("Not yet implemented")
    }

}