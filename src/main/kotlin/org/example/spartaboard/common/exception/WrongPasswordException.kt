package org.example.spartaboard.common.exception

data class WrongEmailOrPasswordException(val emailOrPassword: String): RuntimeException(
    "닉네임 또는 패스워드를 확인해 주세요"
)
