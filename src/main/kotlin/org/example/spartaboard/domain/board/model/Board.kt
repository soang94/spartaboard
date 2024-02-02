package org.example.spartaboard.domain.board.model

import jakarta.persistence.*
import org.example.spartaboard.common.BaseTime
import org.example.spartaboard.domain.board.dto.BoardResponse
import org.example.spartaboard.domain.board.dto.UpdateBoardRequest
import org.example.spartaboard.domain.user.model.User
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "board")
class Board(
    @Column(name = "nickname")
    val nickname: String,

    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    val user: User
): BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun toUpdate(request: UpdateBoardRequest) {
        title = request.title
        content = request.content
    }
}

fun Board.toResponse(): BoardResponse {
    return BoardResponse(
        id = id!!,
        nickname = nickname,
        title = title,
        content = content,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt

    )
}