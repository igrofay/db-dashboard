package five.head.core.data.model.message_from_database

import five.head.core.domain.model.message_from_database.DBErrorModel
import kotlinx.serialization.Serializable

@Serializable
internal data class DBErrorBody(
    override val timestamp: String,
    override val message: String,
    override val solutions: List<String>
) : DBErrorModel