package five.head.dbdashboard.requests_to_db.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import five.head.core.domain.repos.StatisticRepos
import five.head.dbdashboard.common.view_model.AppVM
import five.head.dbdashboard.requests_to_db.model.RequestsToDBEvent
import five.head.dbdashboard.requests_to_db.model.RequestsToDBSideEffect
import five.head.dbdashboard.requests_to_db.model.RequestsToDBState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class RequestsToDBViewModel(
    override val di: DI,
) : AppVM<RequestsToDBState, RequestsToDBSideEffect, RequestsToDBEvent>(), DIAware{
    private val statisticRepos by di.instance<StatisticRepos>()
    override val container: Container<RequestsToDBState, RequestsToDBSideEffect> = viewModelScope
        .container(RequestsToDBState()){
            loadData()
        }
    override fun onEvent(event: RequestsToDBEvent) {
        when(event){
            RequestsToDBEvent.Refresh -> intent {
                reduce { state.copy(isRefreshing = true) }
                loadData()
            }
        }
    }
    private fun loadData() = intent(false){
        coroutineScope {
            async {
                statisticRepos.getQueriesToDB().onSuccess {
                    reduce { state.copy(queries = it, isRefreshing = false) }
                }.onFailure(::onError)
            }.start()
            async {
                statisticRepos.getSessionsToDB().onSuccess {
                    reduce { state.copy(sessions = it, isRefreshing = false) }
                }.onFailure(::onError)
            }.start()
        }
    }
    override fun onError(er: Throwable) {
        Log.e("RequestsToDBViewModel", er.message.toString())
    }
}