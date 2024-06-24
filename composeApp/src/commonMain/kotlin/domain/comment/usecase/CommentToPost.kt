package domain.comment.usecase

import domain.comment.repository.CommentRepository

class CommentToPost(private val repository: CommentRepository) {
    operator fun invoke(postId: String, content: String) = repository.comment(postId, content)
}