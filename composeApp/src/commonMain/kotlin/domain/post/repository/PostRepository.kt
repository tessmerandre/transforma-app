package domain.post.repository

import domain.post.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun findAll(): Flow<List<Post>>
}