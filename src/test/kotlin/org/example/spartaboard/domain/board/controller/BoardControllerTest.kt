package org.example.spartaboard.domain.board.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.example.spartaboard.common.security.jwt.JwtPlugin
import org.example.spartaboard.domain.board.dto.BoardResponse
import org.example.spartaboard.domain.board.service.BoardService
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class BoardControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin
):  DescribeSpec ({
        extensions(SpringExtension)

        afterContainer {
            clearAllMocks()
        }

    val boardService = mockk<BoardService>()

        describe("GET/boards/{boardId}") {
            context("존재하는 ID를 요청할 때") {
                it("200 status code 를 응답한다.") {
                    val boardId = 2L

                    every { boardService.getBoard(any()) } returns BoardResponse(
                        id = boardId,
                        nickname = "test",
                        title = "test code",
                        content = "test code",
                        createdAt = LocalDateTime.now(),
                        updatedAt = LocalDateTime.now(),
                        comment = mutableListOf(),
                    )

                    val jwtToken = jwtPlugin.generateAccessToken(
                        subject = "1",
                        email = "admin@mail.com",
                        role = "ADMIN",
                    )

                    val result = mockMvc.perform(
                        get("/boards/$boardId")
                            .header("Authorization", "Bearer $jwtToken")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                    ).andReturn()

                    result.response.status shouldBe 200

//                    val objectMapper = ObjectMapper()
//                    objectMapper.registerModules(JavaTimeModule())

                    val responseDto = jacksonObjectMapper().readValue(
                        result.response.contentAsString,
                        BoardResponse::class.java
                    )

                    responseDto.id shouldBe boardId
                }
            }
        }
    })

//    DescribeSpec() {
//    @MockkBean
//    private lateinit var boardService: BoardService
//
//    init {
//
//
//        extensions(SpringExtension)
//
//        afterContainer {
//            clearAllMocks()
//        }
//
//        fun objectMapper(): ObjectMapper {
//            val objectMapper = ObjectMapper()
//            objectMapper.registerModules(JavaTimeModule()) // 날짜 및 시간 처리
//            return objectMapper
//        }
//
//
////    val boardService = mockk<BoardService>()
//
//        describe("GET/boards/{boardId}") {
//            context("존재하는 ID를 요청할 때") {
//                it("200 status code 를 응답한다.") {
//                    val boardId = 1L
//
//                    every { boardService.getBoard(any()) } returns BoardResponse(
//                        id = boardId,
//                        nickname = "test",
//                        title = "test code",
//                        content = "test code",
//                        createdAt = LocalDateTime.now(),
//                        updatedAt = LocalDateTime.now(),
//                        comment = emptyList(),
//                    )
//
//                    val jwtToken = jwtPlugin.generateAccessToken(
//                        subject = "1",
//                        email = "admin@mail.com",
//                        role = "ADMIN",
//                    )
//
//                    val result = mockMvc.perform(
//                        get("/boards/$boardId")
//                            .header("Authorization", "Bearer $jwtToken")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .accept(MediaType.APPLICATION_JSON)
//                    ).andReturn()
//
//                    result.response.status shouldBe 200
//
//                    val responseDto = objectMapper().readValue(
//                        result.response.contentAsString,
//                        BoardResponse::class.java
//                    )
//
//                    responseDto.id shouldBe boardId
//                }
//            }
//        }
//    }
//}