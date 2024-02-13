package org.example.spartaboard.domain.board.controller

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.example.spartaboard.domain.board.dto.BoardResponse
import org.example.spartaboard.domain.board.service.BoardService
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class BoardControllerTest2: BehaviorSpec({
    val boardId = 1L
    val boardService = mockk<BoardService>()
    every { boardService.getBoard(boardId) } returns BoardResponse(
        id = boardId,
        nickname = "test",
        title = "test code",
        content = "test code",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        comment = emptyList(),
    )

    val boardController = BoardController(boardService)

    Given("a save board id") {
        val targetBoardId = boardId

        When("execute findBoard") {
            val result = boardController.getBoard(targetBoardId)

            Then("status code should be ok") {
                result.statusCode shouldBe HttpStatus.OK
                result.body?.id shouldBe boardId
            }
        }
    }
})