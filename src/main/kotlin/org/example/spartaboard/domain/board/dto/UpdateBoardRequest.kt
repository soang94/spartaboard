package org.example.spartaboard.domain.board.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class UpdateBoardRequest(
    @field: NotBlank
    @field: Pattern(
        regexp = "^(.{1,500})$",
        message = "최소 1글자부터 최대 500자까지만 입력해주세요."
    )
    private val _title: String?,

    @field: NotBlank
    @field: Pattern(
        regexp = "^(.{1,5000})$",
        message = "최소 1글자부터 최대 5000자까지만 입력입력해주세요."
    )
    private val _content: String?,
) {
    val title: String
        get() = _title!!

    val content: String
        get() = _content!!
}
