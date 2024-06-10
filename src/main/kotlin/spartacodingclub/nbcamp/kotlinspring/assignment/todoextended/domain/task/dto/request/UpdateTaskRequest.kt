package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request

data class UpdateTaskRequest(
    val title: String,
    val content: String,
    val author: String
)