package seq.enitities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document
data class CommentBoard(
    @Id val id: UUID = UUID.randomUUID(),
    val name: String,
    val children: MutableList<Comment> = mutableListOf(),
    val votes: Votes = Votes()
)