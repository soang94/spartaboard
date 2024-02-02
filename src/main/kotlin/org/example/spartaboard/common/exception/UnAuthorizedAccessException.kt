package org.example.spartaboard.common.exception

data class UnAuthorizedAccessException(val auth: Long): RuntimeException(
    "권한이 없습니다."
)
