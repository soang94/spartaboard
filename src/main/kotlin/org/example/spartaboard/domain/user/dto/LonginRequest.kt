package org.example.spartaboard.domain.user.dto

data class LonginRequest(
    val email: String,
    val password: String,
)