package org.example.spartaboard.domain.comment.controller

import io.swagger.v3.oas.annotations.Operation
import org.example.spartaboard.common.security.jwt.UserPrincipal
import org.example.spartaboard.domain.comment.dto.CommentRequest
import org.example.spartaboard.domain.comment.dto.CommentResponse
import org.example.spartaboard.domain.comment.service.CommentService
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
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("boards/{boardId}/comments/")
class CommentController(
    private val commentService: CommentService
) {

    @Operation(summary = "댓글 목록")
    @GetMapping
    fun getCommentList(
        @PathVariable boardId: Long
    ): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentList(boardId))
    }

    @Operation(summary = "댓글 단건")
    @GetMapping("/{commentId}")
    fun getComment(
        @PathVariable boardId: Long,
        @PathVariable commentId: Long,
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getComment(boardId, commentId))
    }

    @Operation(summary = "댓글 생성")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @PostMapping
    fun createComment(
        @PathVariable boardId: Long,
        @RequestBody commentRequest: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(boardId, commentRequest))
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable boardId: Long,
        @PathVariable commentId: Long,
        @RequestBody commentRequest: CommentRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<CommentResponse> {
        val userId = userPrincipal.id
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(boardId, commentId, userId, commentRequest))
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable boardId: Long,
        @PathVariable commentId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Any> {
        val userId = userPrincipal.id
        commentService.deleteComment(boardId, commentId, userId)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("해당 댓글을 삭제했습니다.")
    }


}