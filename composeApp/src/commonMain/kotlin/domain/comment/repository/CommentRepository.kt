package domain.comment.repository

import kotlinx.coroutines.flow.Flow

interface CommentRepository {
    fun comment(postId: String, content: String): Flow<Result<Unit>>
    fun reply(commentId: String, content: String): Flow<Result<Unit>>
}