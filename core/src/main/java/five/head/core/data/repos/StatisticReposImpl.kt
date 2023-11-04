package five.head.core.data.repos

import five.head.core.data.data_source.network.AnalyticsApi
import five.head.core.data.model.statistic.DatabaseInfoBody
import five.head.core.data.model.statistic.HardwareLoadBody
import five.head.core.domain.model.statistic.DatabaseInfoModel
import five.head.core.domain.model.statistic.HardwareLoadModel
import five.head.core.domain.repos.StatisticRepos
import io.ktor.client.call.body

internal class StatisticReposImpl(
    private val analyticsApi: AnalyticsApi,
) : StatisticRepos{
    override suspend fun getDatabaseInfo(): Result<DatabaseInfoModel> =
        runCatching { analyticsApi.getDatabaseInfo().body<DatabaseInfoBody>() }

    override suspend fun getHardwareLoad(): Result<HardwareLoadModel> =
        runCatching { analyticsApi.getHardwareLoad().body<HardwareLoadBody>() }
}