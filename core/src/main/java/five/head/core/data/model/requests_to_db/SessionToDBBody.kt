package five.head.core.data.model.requests_to_db

import five.head.core.domain.model.requests_to_db.SessionToDBModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SessionToDBBody(
    @SerialName("user_name")
    override val userName: String,
    @SerialName("session_count")
    override val sessionCount: Int,
    @SerialName("max_query_duration")
    override val maxQueryDuration: String
): SessionToDBModel