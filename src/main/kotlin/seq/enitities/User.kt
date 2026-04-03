package seq.enitities
import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val name: String? = null,
    val email: String = "email@example.com"
)