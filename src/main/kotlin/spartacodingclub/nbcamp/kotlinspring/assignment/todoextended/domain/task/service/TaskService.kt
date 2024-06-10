package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity.Task
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity.from
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity.toResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity.updateFrom
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.repository.TaskRepository

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {

    fun createTask(request: CreateTaskRequest): TaskResponse =
        taskRepository.save(Task.from(request)).toResponse()


    fun readAllTasks(): List<TaskResponse> =
        taskRepository.findAll()
            .sortedByDescending { it.createdAt }
            .map { it.toResponse() }

    fun readTask(taskId: Long): TaskResponse =
        taskRepository.findByIdOrNull(taskId)?.toResponse()
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
        taskRepository.delete(
            taskRepository.findByIdOrNull(taskId)
                ?: throw IllegalArgumentException("Target Task not found")
        )
    }


}