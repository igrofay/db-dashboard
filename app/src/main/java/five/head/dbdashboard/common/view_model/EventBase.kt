package five.head.dbdashboard.common.view_model

import five.head.dbdashboard.common.model.mvi.UIEvent

interface EventBase<T: UIEvent> {
    fun onEvent(event: T)
}