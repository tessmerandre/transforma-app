package domain.feed.model

import domain.post.model.Post

data class Feed(
    val posts: List<Post>
)