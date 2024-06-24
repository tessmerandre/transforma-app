package domain.comment.model

import kotlinx.datetime.LocalDateTime

data class Comment(
    val id: String,
    val content: String,
    val isReply: Boolean,
    val createdAt: LocalDateTime,
    val replies: List<Comment>
)