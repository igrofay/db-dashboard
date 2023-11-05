package five.head.dbdashboard.requests_to_db.model

import five.head.core.domain.model.requests_to_db.QueriesToDBModel
import five.head.core.domain.model.requests_to_db.SessionToDBModel
import five.head.dbdashboard.common.model.mvi.UIState

data class RequestsToDBState(
    val queries: List<QueriesToDBModel> = listOf(),
    val sessions: List<SessionToDBModel> = listOf(),
    val isRefreshing: Boolean = true,
) : UIState() {
    companion object {
        const val numberOfListData = 2
    }
}