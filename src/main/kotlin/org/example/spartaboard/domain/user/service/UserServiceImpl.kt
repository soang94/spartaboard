package org.example.spartaboard.domain.user.service

import org.example.spartaboard.domain.user.dto.LoginRequest
import org.example.spartaboard.domain.user.dto.LoginResponse
import org.example.spartaboard.domain.user.dto.SignupRequest
import org.example.spartaboard.domain.user.dto.UserResponse
import org.example.spartaboard.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    override fun getUserList(): List<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun getUser(userId: Long): UserResponse {
        TODO("Not yet implemented")
    }

    override fun signup(request: SignupRequest): UserResponse {
        TODO("Not yet implemented")
    }

    override fun login(request: LoginRequest): LoginResponse {
        TODO("Not yet implemented")
    }

}