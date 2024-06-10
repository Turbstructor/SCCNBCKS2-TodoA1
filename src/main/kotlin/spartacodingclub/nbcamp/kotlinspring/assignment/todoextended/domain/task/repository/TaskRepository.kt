package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.entity.Task

@Repository
interface TaskRepository: JpaRepository<Task, Long> {
}