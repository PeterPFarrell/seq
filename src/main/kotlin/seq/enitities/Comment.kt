package seq.enitities

import org.springframework.data.annotation.Id
import seq.Constants
import java.util.UUID

data class Comment(
    @Id val id: UUID = UUID.randomUUID(),
    val parentId: UUID,
    val name: String = Constants.DEFAULT_USER_NAME,
    val body: String,
    val votes: Votes = Votes()
    )