package org.example.spartaboard.domain.comment.model

import jakarta.persistence.*
import org.example.spartaboard.common.BaseTime
import org.example.spartaboard.domain.board.model.Board
import org.example.spartaboard.domain.comment.dto.CommentRequest
import org.example.spartaboard.domain.comment.dto.CommentResponse
import org.example.spartaboard.domain.user.model.User
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "comment")
class Comment(
    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "content")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    var board: Board,

): BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun toUpdate(request: CommentRequest) {
        content = request.content
    }
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        nickname = nickname,
        content = content,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}