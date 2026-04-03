package seq.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import seq.enitities.CommentBoard
import java.util.UUID
@Repository
interface CommentBoardRepository : MongoRepository<CommentBoard, UUID>