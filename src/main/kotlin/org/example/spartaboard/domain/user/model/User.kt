package org.example.spartaboard.domain.user.model

import jakarta.persistence.*
import org.example.spartaboard.common.BaseTime
import org.example.spartaboard.domain.user.dto.UserResponse
import org.example.spartaboard.domain.user.repository.UserRole

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