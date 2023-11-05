package five.head.core.data.model.requests_to_db

import five.head.core.domain.model.requests_to_db.QueriesToDBModel
import kotlinx.serialization.Serializable

@Serializable
internal data class QueriesToDBBody(
    override val queryText: String,
    override val queryCount: Int,
    override val maxQueryDuration: String,
) : QueriesToDBModel
