package org.example.spartaboard.domain.user.service

import org.example.spartaboard.domain.user.dto.LoginRequest
import org.example.spartaboard.domain.user.dto.LoginResponse
import org.example.spartaboard.domain.user.dto.SignupRequest
import org.example.spartaboard.domain.user.dto.UserResponse

interface UserService {
    fun getUserList(): List<UserResponse>

    fun getUser(userId: Long): UserResponse

    fun signup(request: SignupRequest): UserResponse

    fun login(request: LoginRequest): LoginResponse
}