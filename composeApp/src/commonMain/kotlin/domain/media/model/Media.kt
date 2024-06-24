package domain.media.model

data class Media(
    val id: String,
    val url: String,
    val type: MediaType
) {
    enum class MediaType {
        IMAGE, VIDEO
    }
}