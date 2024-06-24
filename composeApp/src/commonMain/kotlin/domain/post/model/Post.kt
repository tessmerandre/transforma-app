package domain.post.model

import domain.media.model.Media
import domain.user.model.User

data class Post(
    val id: Int,
    val title: String,
    val description: String,
    val medias: List<Media>,
    val owner: User
)