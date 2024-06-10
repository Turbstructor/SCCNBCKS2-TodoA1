package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.request.CreateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.request.UpdateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.response.CommentDetailedResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.response.CommentResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity.Task
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity.toResponse
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Entity
@Table(name = "comment")
class Comment(

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    var task: Task,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "author", nullable = false)
    var author: String,

    @Column(name = "password", nullable = false)
    var password: String
) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "created_at", nullable = false)
    val createdAt: ZonedDateTime = LocalDateTime.now().atZone(ZoneId.of("UTC"))

    @Column(name = "updated_at", nullable = false)
    var updatedAt: ZonedDateTime = LocalDateTime.now().atZone(ZoneId.of("UTC"))


    companion object
}

fun Comment.Companion.from(task: Task, request: CreateCommentRequest) = Comment(task, request.content, request.author, request.password)

fun Comment.updateFrom(request: UpdateCommentRequest) {
    if (author != request.author || password != request.password) throw RuntimeException("Unauthorized Access")
    content = request.content

    updatedAt = LocalDateTime.now().atZone(ZoneId.of("UTC"))
}

fun Comment.toResponse() = CommentResponse(
    id = id!!,
    content = content,
    author = author,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun Comment.toDetailedResponse() = CommentDetailedResponse(
    comment = this.toResponse(),
    taskRelated = task.toResponse()
)
