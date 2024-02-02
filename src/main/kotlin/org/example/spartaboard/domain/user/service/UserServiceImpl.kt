package org.example.spartaboard.domain.user.service

import org.example.spartaboard.common.exception.InvalidRoleException
import org.example.spartaboard.common.exception.ModelNotFoundException
import org.example.spartaboard.common.exception.WrongEmailOrPasswordException
import org.example.spartaboard.common.security.jwt.JwtPlugin
import org.example.spartaboard.domain.user.dto.LoginRequest
import org.example.spartaboard.domain.user.dto.LoginResponse
import org.example.spartaboard.domain.user.dto.SignupRequest
import org.example.spartaboard.domain.user.dto.UserResponse
import org.example.spartaboard.domain.user.model.User
import org.example.spartaboard.domain.user.model.checkedEmailOrNicknameExists
import org.example.spartaboard.domain.user.model.checkedLoginPassword
import org.example.spartaboard.domain.user.model.toResponse
import org.example.spartaboard.domain.user.repository.UserRepository
import org.example.spartaboard.domain.user.repository.UserRole
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
): UserService {
    override fun getUserList(): List<UserResponse> {
        return userRepository.findAll().map { it.toResponse() }
    }

    override fun getUser(userId: Long): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        return user.toResponse()
    }

    @Transactional
    override fun signup(request: SignupRequest): UserResponse {
        checkedEmailOrNicknameExists(request.email, request.nickname, userRepository)

        return userRepository.save(
            User(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                name = request.name,
                nickname = request.nickname,
                info = request.info,
                role = when (request.role) {
                    "ADMIN" -> UserRole.ADMIN
                    "MEMBER" -> UserRole.MEMBER
                    else  -> throw InvalidRoleException(request.role)
                }
            )
        ).toResponse()
    }

    @Transactional
    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw WrongEmailOrPasswordException(request.email)
        checkedLoginPassword(user.password, request.password, passwordEncoder)

            return LoginResponse(
                accessToken = jwtPlugin.generateAccessToken(
                    subject = user.id.toString(),
                    email = user.email,
                    role = user.role.name
                )
            )
    }

}