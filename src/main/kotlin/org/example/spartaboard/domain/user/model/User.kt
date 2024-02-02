package org.example.spartaboard.domain.user.model

import jakarta.persistence.*
import org.example.spartaboard.common.BaseTime
import org.example.spartaboard.common.exception.AlreadyExistException
import org.example.spartaboard.common.exception.WrongEmailOrPasswordException
import org.example.spartaboard.domain.user.dto.UserResponse
import org.example.spartaboard.domain.user.repository.UserRepository
import org.example.spartaboard.domain.user.repository.UserRole
import org.springframework.security.crypto.password.PasswordEncoder

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "info")
    var info: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: UserRole,

    ): BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        email = email,
        name = name,
        nickname = nickname,
        info = info,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt,
        role = role.name,
    )
}

fun checkedEmailOrNicknameExists(email: String, nickname: String, userRepository: UserRepository) {
    if (userRepository.existsByEmail(email)) {
        throw AlreadyExistException(email)
    }

    if (userRepository.existsByNickname(nickname)) {
        throw AlreadyExistException(nickname)
    }
}

fun checkedLoginPassword(password: String, inputPassword: String, passwordEncoder: PasswordEncoder) {
    if(!passwordEncoder.matches(inputPassword, password)) {
        throw WrongEmailOrPasswordException(inputPassword)
    }
}