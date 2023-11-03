package five.head.dbdashboard.common.view_model

import androidx.lifecycle.ViewModel
import five.head.dbdashboard.common.model.mvi.UIEvent
import five.head.dbdashboard.common.model.mvi.UISideEffect
import five.head.dbdashboard.common.model.mvi.UIState
import org.orbitmvi.orbit.ContainerHost

abstract class AppVM<State : UIState, SideEffect : UISideEffect, Event : UIEvent> :
    ContainerHost<State, SideEffect>, EventBase<Event>, ViewModel() {
    protected abstract fun onError(er: Throwable)
}