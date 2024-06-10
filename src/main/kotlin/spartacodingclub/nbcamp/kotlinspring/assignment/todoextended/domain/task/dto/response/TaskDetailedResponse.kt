package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.response

import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.response.CommentResponse

data class TaskDetailedResponse(
    val task: TaskResponse,
    val comments: List<CommentResponse>
)
