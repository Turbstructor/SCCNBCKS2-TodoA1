package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity

import jakarta.persistence.*
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.entity.Comment
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.entity.toResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.response.TaskDetailedResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.response.TaskResponse
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Entity
@Table(name = "task")
class Task(

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "author", nullable = false)
    var author: String
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "is_done", nullable = false)
    var isDone: Boolean = false

    @Column(name = "created_at")
    val createdAt: ZonedDateTime = LocalDateTime.now().atZone(ZoneId.of("UTC"))

    @Column(name = "updated_at")
    var updatedAt: ZonedDateTime = LocalDateTime.now().atZone(ZoneId.of("UTC"))


    companion object

    fun toggleCompletion() {
        isDone = !isDone
    }
}

fun Task.Companion.from(request: CreateTaskRequest) = Task(request.title, request.content, request.author)

fun Task.updateFrom(request: UpdateTaskRequest) {
    title = request.title
    content = request.content
    author = request.author

    updatedAt = LocalDateTime.now().atZone(ZoneId.of("UTC"))
}

fun Task.toResponse() = TaskResponse(
    id = id!!,
    title = title,
    content = content,
    isDone = isDone,
    author = author,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Task.toDetailedResponse(comments: List<Comment>) = TaskDetailedResponse(
    task = this.toResponse(),
    comments = comments.map { it.toResponse() }
)