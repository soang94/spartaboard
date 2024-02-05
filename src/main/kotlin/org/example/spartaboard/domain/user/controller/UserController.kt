package org.example.spartaboard.domain.user.controller

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.example.spartaboard.domain.user.dto.LoginRequest
import org.example.spartaboard.domain.user.dto.SignupRequest
import org.example.spartaboard.domain.user.dto.UserResponse
import org.example.spartaboard.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService
) {

    @Operation(summary = "회원 전체 조회")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @GetMapping("/users")
    fun getUserList(): ResponseEntity<List<UserResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getUserList())
    }

    @Operation(summary = "회원 단건 조회 / 프로필")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @GetMapping("/users/{userId}")
    fun getUser(
        @PathVariable userId: Long
    ): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getUser(userId))
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    fun signup(
      @Valid @RequestBody signupRequest: SignupRequest
    ): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signup(signupRequest))
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(
        @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<Any> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(loginRequest))
    }

}