package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request

data class CreateTaskRequest(
    val title: String,
    val content: String,
    val author: String
)