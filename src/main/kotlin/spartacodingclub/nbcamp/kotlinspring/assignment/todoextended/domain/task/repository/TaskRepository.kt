package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity.Task

@Repository
interface TaskRepository: CrudRepository<Task, Long> {
}