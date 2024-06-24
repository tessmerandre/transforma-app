package data.comment.repository

import domain.comment.repository.CommentRepository
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CommentRepositoryImpl(
    private val supabase: SupabaseClient
) : CommentRepository {
    override fun comment(postId: String, content: String): Flow<Result<Unit>> {
        return flowOf()
    }

    override fun reply(commentId: String, content: String): Flow<Result<Unit>> {
        return flowOf()
    }
}