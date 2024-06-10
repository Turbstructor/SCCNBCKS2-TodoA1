package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity

import jakarta.persistence.*
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request.UpdateTaskRequest
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

    @Column(name = "created_at")
    val createdAt: ZonedDateTime = LocalDateTime.now().atZone(ZoneId.of("UTC"))

    @Column(name = "updated_at")
    var updatedAt: ZonedDateTime = LocalDateTime.now().atZone(ZoneId.of("UTC"))


    companion object
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
    author = author,
    createdAt = createdAt,
    updatedAt = updatedAt
)