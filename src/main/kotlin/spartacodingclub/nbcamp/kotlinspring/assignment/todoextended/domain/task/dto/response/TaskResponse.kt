package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.response

import java.time.ZonedDateTime

data class TaskResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime
)