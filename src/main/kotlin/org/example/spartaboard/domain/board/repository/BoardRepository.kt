package org.example.spartaboard.domain.board.repository

import org.example.spartaboard.domain.board.model.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long>, CustomBoardRepository {
}