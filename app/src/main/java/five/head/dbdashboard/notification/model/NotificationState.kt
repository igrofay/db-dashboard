package five.head.dbdashboard.notification.model

import five.head.core.domain.model.message_from_database.DBErrorModel
import five.head.dbdashboard.common.model.mvi.UIState
import java.text.SimpleDateFormat
import java.util.Locale

data class NotificationState(
    val errors: List<DBErrorData> = listOf(),
    val isRefreshing: Boolean = true,
) : UIState(){

}