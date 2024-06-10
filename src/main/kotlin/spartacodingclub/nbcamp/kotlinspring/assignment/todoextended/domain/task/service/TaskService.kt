package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.request.CreateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.request.UpdateCommentRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.dto.response.CommentDetailedResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.entity.*
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.repository.CommentRepository
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.response.TaskDetailedResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity.*
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.repository.TaskRepository

@Service
class TaskService(
    private val taskRepository: TaskRepository,
    private val commentRepository: CommentRepository
) {

    // Tasks

    fun createTask(request: CreateTaskRequest): TaskResponse =
        taskRepository.save(Task.from(request)).toResponse()


    fun readAllTasks(): List<TaskResponse> =
        taskRepository.findAll()
            .sortedByDescending { it.createdAt }
            .map { it.toResponse() }

    fun readTask(taskId: Long): TaskDetailedResponse =
        taskRepository.findByIdOrNull(taskId)?.
        toDetailedResponse(commentRepository.findAllByTaskId(taskId))
            ?: throw IllegalArgumentException("Target Task not found")


    fun toggleTaskCompletion(taskId: Long) {
        val targetTask = taskRepository.findByIdOrNull(taskId)
            ?: throw IllegalArgumentException("Target Task not found")

        targetTask.toggleCompletion()

        taskRepository.save(targetTask)
    }


    fun updateTask(taskId: Long, request: UpdateTaskRequest): TaskResponse {
        val targetTask = taskRepository.findByIdOrNull(taskId)
            ?: throw IllegalArgumentException("Target Task not found")

        targetTask.updateFrom(request)

        return taskRepository.save(targetTask).toResponse()
    }


    fun deleteTask(taskId: Long) {
        commentRepository.deleteAll(commentRepository.findAllByTaskId(taskId))
        taskRepository.delete(
            taskRepository.findByIdOrNull(taskId)
                ?: throw IllegalArgumentException("Target Task not found")
        )
    }


    // Comments

    fun createComment(taskId: Long, request: CreateCommentRequest): CommentDetailedResponse =
        taskRepository.findByIdOrNull(taskId)?.let { targetTask ->
            commentRepository.save(Comment.from(targetTask, request)).toDetailedResponse()
        } ?: throw IllegalArgumentException("Target Task not found")


    fun updateComment(taskId: Long, commentId: Long, request: UpdateCommentRequest): CommentDetailedResponse {
        val targetComment = commentRepository.findByTaskIdAndId(taskId, commentId)
            ?: throw IllegalArgumentException("Target Comment not found")

        targetComment.updateFrom(request)

        return commentRepository.save(targetComment).toDetailedResponse()
    }


    fun deleteComment(taskId: Long, commentId: Long) {
        commentRepository.delete(
            commentRepository.findByTaskIdAndId(taskId, commentId)
                ?: throw IllegalArgumentException("Target Comment not found")
        )
    }

}