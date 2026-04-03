package seq.enitities

import seq.Constants
import java.util.UUID

data class Comment(
    val id: UUID = UUID.randomUUID(),
    val name: String = Constants.DEFAULT_USER_NAME,
    val body: String,
    val votes: Votes = Votes()
    )