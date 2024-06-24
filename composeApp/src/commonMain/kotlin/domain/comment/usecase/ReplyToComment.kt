package domain.comment.usecase

import domain.comment.repository.CommentRepository

class ReplyToComment(private val repository: CommentRepository) {
    operator fun invoke(commentId: String, content: String) = repository.comment(commentId, content)
}