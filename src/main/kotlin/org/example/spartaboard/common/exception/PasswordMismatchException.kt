package org.example.spartaboard.common.exception

data class PasswordMismatchException(val password: String): RuntimeException(
    "비밀번호가 일치하지 않습니다."
)
