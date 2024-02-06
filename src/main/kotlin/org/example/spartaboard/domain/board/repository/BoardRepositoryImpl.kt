package org.example.spartaboard.domain.board.repository

import org.example.spartaboard.common.querydsl.QueryDslSupport
import org.example.spartaboard.domain.board.model.Board
import org.example.spartaboard.domain.board.model.QBoard
import org.springframework.stereotype.Repository

@Repository
class BoardRepositoryImpl: QueryDslSupport(), CustomBoardRepository {

    protected val board = QBoard.board

   override fun searchBoardListByTitle(title: String): List<Board> {
        return queryFactory.selectFrom(board)
            .where(board.title.containsIgnoreCase(title))
            .fetch()
    }
}