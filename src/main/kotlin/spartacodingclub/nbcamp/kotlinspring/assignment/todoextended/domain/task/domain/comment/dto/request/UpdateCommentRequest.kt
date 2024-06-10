package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.request

data class UpdateCommentRequest(
    val content: String,
    val author: String,
    val password: String
)
