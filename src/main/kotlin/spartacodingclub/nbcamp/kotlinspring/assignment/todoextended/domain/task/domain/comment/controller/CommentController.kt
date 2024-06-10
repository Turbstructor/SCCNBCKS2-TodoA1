package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.request.CreateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.request.UpdateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.response.CommentDetailedResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.service.TaskService

@RestController
@RequestMapping("/api/tasks/{taskId}/comments")
class CommentController(
    private val taskService: TaskService
) {

    @PostMapping
    fun createComment(@PathVariable taskId: Long, @RequestBody request: CreateCommentRequest): ResponseEntity<CommentDetailedResponse> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(taskService.createComment(taskId, request))


    @PutMapping("/{commentId}")
    fun updateCOmment(@PathVariable taskId: Long, @PathVariable commentId: Long, @RequestBody request: UpdateCommentRequest): ResponseEntity<CommentDetailedResponse> =
        ResponseEntity.status(HttpStatus.OK)
            .body(taskService.updateComment(taskId, commentId, request))


    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable taskId: Long, @PathVariable commentId: Long): ResponseEntity<Unit> =
        ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(taskService.deleteComment(taskId, commentId))

}