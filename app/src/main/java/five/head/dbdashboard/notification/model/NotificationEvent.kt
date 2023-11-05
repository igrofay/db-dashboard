package five.head.dbdashboard.notification.model

import five.head.dbdashboard.common.model.mvi.UIEvent

sealed class NotificationEvent : UIEvent(){
    data object Refresh: NotificationEvent()
}