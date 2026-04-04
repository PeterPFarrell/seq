package seq.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import seq.enitities.Comment
import java.util.UUID

@Repository
interface CommentRepository : MongoRepository<Comment, UUID> {
    fun findAllByParentId(parentId: UUID): List<Comment>
}