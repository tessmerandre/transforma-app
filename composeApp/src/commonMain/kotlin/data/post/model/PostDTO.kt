package data.post.model

import domain.post.model.Post
import domain.user.model.User
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class PostDTO(
    val id: Int,
    val description: String,
    val title: String,
    @JsonNames("created_at")
    val createdAt: String
) {
    fun toModel(): Post = Post(
        id = id,
        description = description,
        title = title,
        owner = User("das", "dsada", "dsadas"),
        medias = emptyList()
    )
}

fun List<PostDTO>.toModel(): List<Post> = map { it.toModel() }