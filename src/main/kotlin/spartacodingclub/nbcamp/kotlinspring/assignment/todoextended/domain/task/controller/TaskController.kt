package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request.CreateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.request.UpdateTaskRequest
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.dto.response.TaskResponse
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.service.TaskService

@RestController
@RequestMapping("/api/tasks")
class TaskController(
    private val taskService: TaskService
) {

    @PostMapping
    fun createTask(@RequestBody request: CreateTaskRequest): ResponseEntity<TaskResponse> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(taskService.createTask(request))


    @GetMapping
    fun readAllTasks(): ResponseEntity<List<TaskResponse>> =
        ResponseEntity.status(HttpStatus.OK)
            .body(taskService.readAllTasks())

    @GetMapping("/{taskId}")
    fun readTask(@PathVariable taskId: Long): ResponseEntity<TaskResponse> =
        ResponseEntity.status(HttpStatus.OK)
            .body(taskService.readTask(taskId))


    @PatchMapping("/{taskId}")
    fun toggleTaskCompletion(@PathVariable taskId: Long): ResponseEntity<Unit> =
        ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(taskService.toggleTaskCompletion(taskId))


    @PutMapping("/{taskId}")
    fun updateTask(@PathVariable taskId: Long, @RequestBody request: UpdateTaskRequest): ResponseEntity<TaskResponse> =
        ResponseEntity.status(HttpStatus.OK)
            .body(taskService.updateTask(taskId, request))


    @DeleteMapping("/{taskId}")
    fun deleteTask(@PathVariable taskId: Long): ResponseEntity<Unit> =
        ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(taskService.deleteTask(taskId))

}