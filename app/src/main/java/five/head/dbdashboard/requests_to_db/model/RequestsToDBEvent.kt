package five.head.dbdashboard.requests_to_db.model

import five.head.dbdashboard.common.model.mvi.UIEvent

sealed class RequestsToDBEvent : UIEvent() {
    data object Refresh: RequestsToDBEvent()
}