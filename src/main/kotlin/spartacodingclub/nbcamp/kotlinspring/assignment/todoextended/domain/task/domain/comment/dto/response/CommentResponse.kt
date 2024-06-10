package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.response

import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.response.TaskResponse
import java.time.ZonedDateTime

data class CommentResponse(
    val id: Long,
    val content: String,
    val author: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val taskRelated: TaskResponse
)