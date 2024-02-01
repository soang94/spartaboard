package org.example.spartaboard.common.exception

data class InvalidRoleException(val role: String): RuntimeException(
    "입력하신 $role 은 존재하지 않습니다."
)