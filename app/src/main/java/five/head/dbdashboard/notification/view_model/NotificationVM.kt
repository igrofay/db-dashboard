package five.head.dbdashboard.notification.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import five.head.core.domain.repos.NotificationRepos
import five.head.dbdashboard.common.view_model.AppVM
import five.head.dbdashboard.notification.model.DBErrorData.Companion.fromListModelToListDBErrorData
import five.head.dbdashboard.notification.model.NotificationEvent
import five.head.dbdashboard.notification.model.NotificationSideEffect
import five.head.dbdashboard.notification.model.NotificationState
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class NotificationVM(
    override val di: DI
) : AppVM<NotificationState, NotificationSideEffect, NotificationEvent>(), DIAware {
    private val notificationRepos by di.instance<NotificationRepos>()
    override val container: Container<NotificationState, NotificationSideEffect> = viewModelScope
        .container(NotificationState()) { loadData() }

    override fun onEvent(event: NotificationEvent) {
        when (event) {
            NotificationEvent.Refresh -> intent {
                reduce { state.copy(isRefreshing = true) }
                loadData()
            }

        }
    }

    private fun loadData() = intent(false) {
        notificationRepos.getListDBError().onSuccess {
            reduce {
                state.copy(
                    errors = it.fromListModelToListDBErrorData(),
                    isRefreshing = false
                )
            }
        }.onFailure(::onError)
    }

    override fun onError(er: Throwable) {
        Log.e("NotificationVM", er.message.toString())
    }
}