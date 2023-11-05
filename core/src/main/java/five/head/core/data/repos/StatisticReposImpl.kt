package five.head.core.data.repos

import five.head.core.data.data_source.network.AnalyticsApi
import five.head.core.data.model.requests_to_db.QueriesToDBBody
import five.head.core.data.model.requests_to_db.SessionToDBBody
import five.head.core.data.model.statistic.DatabaseInfoBody
import five.head.core.data.model.statistic.HardwareLoadBody
import five.head.core.domain.model.requests_to_db.QueriesToDBModel
import five.head.core.domain.model.requests_to_db.SessionToDBModel
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

    override suspend fun getQueriesToDB(): Result<List<QueriesToDBModel>> =
        runCatching { analyticsApi.getQueriesToDB().body<List<QueriesToDBBody>>() }

    override suspend fun getSessionsToDB(): Result<List<SessionToDBModel>> =
        runCatching { analyticsApi.getSessionsToDB().body<List<SessionToDBBody>>() }
}