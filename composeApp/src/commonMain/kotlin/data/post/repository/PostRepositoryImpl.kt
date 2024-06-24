package data.post.repository

import data.post.model.PostDTO
import data.post.model.toModel
import domain.post.model.Post
import domain.post.repository.PostRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable

class PostRepositoryImpl(
    private val supabase: SupabaseClient
) : PostRepository {
    override fun findAll(): Flow<List<Post>> {
        return flow {
            val result = supabase.from("post").select()
            val posts = result.decodeList<PostDTO>()
            emit(posts.toModel())
        }
    }
}