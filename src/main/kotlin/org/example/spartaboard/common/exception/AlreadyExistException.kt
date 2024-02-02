package org.example.spartaboard.common.exception

data class AlreadyExistException(val a: String): RuntimeException(
    "$a 는 이미 존재합니다."
)