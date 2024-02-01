package org.example.spartaboard.domain.user.repository

import org.example.spartaboard.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}