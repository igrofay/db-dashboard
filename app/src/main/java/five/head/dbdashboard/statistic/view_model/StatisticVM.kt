package five.head.dbdashboard.statistic.view_model

import android.util.Log
import androidx.lifecycle.viewModelScope
import five.head.core.domain.repos.StatisticRepos
import five.head.dbdashboard.common.view_model.AppVM
import five.head.dbdashboard.statistic.model.StatisticEvent
import five.head.dbdashboard.statistic.model.StatisticSideEffect
import five.head.dbdashboard.statistic.model.StatisticState
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce

class StatisticVM(
    override val di: DI
) : AppVM<StatisticState, StatisticSideEffect, StatisticEvent>(), DIAware {
    private val statisticRepos by di.instance<StatisticRepos>()

    override val container: Container<StatisticState, StatisticSideEffect> = viewModelScope
        .container(StatisticState()) {
            loadData()
        }

    override fun onEvent(event: StatisticEvent) {
    }

    private fun loadData() = intent(false) {
        coroutineScope {
            while (true) {
                async {
                    statisticRepos.getDatabaseInfo().onSuccess { data ->
                        reduce { state.copy(databaseInfo = data) }
                    }.onFailure(::onError)
                }.start()
                async {
                    statisticRepos.getHardwareLoad().onSuccess { data ->
                        reduce {
                            state.copy(
                                cpuUsagePercentage = data
                                    .cpuUsagePercentage,
                                ramLoad = state
                                    .ramLoad.add(data.maxRamSizeMb - data.availableRamMb),
                                maxRamSizeMb = data.maxRamSizeMb,
                                driveUsages = data.driveUsages
                            )
                        }
                    }.onFailure(::onError)
                }.start()
                delay(5_000)
            }
        }
    }

    override fun onError(er: Throwable) {
        Log.e("StatisticVM", er.message.toString())
    }
}