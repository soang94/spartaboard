package org.example.spartaboard.domain.board.repository

import org.example.spartaboard.domain.board.model.Board

interface CustomBoardRepository {

    fun searchBoardListByTitle(title: String): List<Board>
}