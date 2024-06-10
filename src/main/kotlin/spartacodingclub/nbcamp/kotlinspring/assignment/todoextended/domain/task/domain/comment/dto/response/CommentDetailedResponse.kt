package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.response

import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.response.TaskResponse

data class CommentDetailedResponse(
    val comment: CommentResponse,
    val taskRelated: TaskResponse
)