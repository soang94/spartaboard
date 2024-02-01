package org.example.spartaboard.common.exception

data class WrongPasswordException(val password: String): RuntimeException(
    "비밀번호가 틀립니다."
)
