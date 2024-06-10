package spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import spartacodingclub.nbcamp.kotlinspring.assignment.todoextended.domain.task.domain.comment.entity.Comment

@Repository
interface CommentRepository: JpaRepository<Comment, Long> {
    fun findByTaskIdAndId(taskId: Long, id: Long): Comment?
    fun findAllByTaskId(taskId: Long): List<Comment>
}