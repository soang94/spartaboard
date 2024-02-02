package org.example.spartaboard.domain.board.model

import jakarta.persistence.*
import org.example.spartaboard.common.BaseTime
import org.example.spartaboard.domain.board.dto.BoardResponse
import org.example.spartaboard.domain.board.dto.UpdateBoardRequest
import org.example.spartaboard.domain.comment.model.Comment
import org.example.spartaboard.domain.user.model.User
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "board")
class Board(
    @Column(name = "nickname")
    var nickname: String,

    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    var user: User,

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf(),

): BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun toUpdate(request: UpdateBoardRequest) {
        title = request.title
        content = request.content
    }

    fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun removeComment(comment: Comment) {
        comments.remove(comment)
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