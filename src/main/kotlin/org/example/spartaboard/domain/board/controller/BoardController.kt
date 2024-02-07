package org.example.spartaboard.domain.board.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.example.spartaboard.common.security.jwt.UserPrincipal
import org.example.spartaboard.domain.board.dto.BoardResponse
import org.example.spartaboard.domain.board.dto.CreateBoardRequest
import org.example.spartaboard.domain.board.dto.UpdateBoardRequest
import org.example.spartaboard.domain.board.service.BoardService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/boards")
class BoardController(
    private val boardService: BoardService
) {

    // querydsl
    @GetMapping("/search")
    fun searchBoardList(
        @RequestParam(value = "title") title: String
    ): ResponseEntity<List<BoardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.searchBoardList(title))
    }


    // 일반 crud
    @Operation(summary = "게시물 전체 리스트")
    @GetMapping
    fun getBoardList(
        @PageableDefault(size = 4, sort = ["createdAt"], direction = Sort.Direction.DESC)
        pageable: Pageable
    ): ResponseEntity<Page<BoardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.getBoardList(pageable))
    }

    @Operation(summary = "게시물 단건 조회")
    @GetMapping("/{boardId}")
    fun getBoard(
        @PathVariable boardId: Long
    ): ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.getBoard(boardId))
    }

    @Operation(summary = "게시물 작성")
    @PreAuthorize("hasRole('MEMBER') or hasRole('ADMIN')")
    @PostMapping
    fun createBoard(
       @Valid @RequestBody createBoardRequest: CreateBoardRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(boardService.createBoard(createBoardRequest, userPrincipal.id))
    }

    @Operation(summary = "게시물 수정")
    @PutMapping("/{boardId}")
    fun updateBoard(
       @Valid @PathVariable boardId: Long,
        @RequestBody updateBoardRequest: UpdateBoardRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.updateBoard(boardId ,updateBoardRequest, userPrincipal.id))
    }

    @Operation(summary = "게시물 삭제")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @DeleteMapping("/{boardId}")
    fun deleteBoard(
        @PathVariable boardId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Any> {
        boardService.delete(boardId, userPrincipal.id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("해당 게시물이 삭제되었습니다.")
    }
}