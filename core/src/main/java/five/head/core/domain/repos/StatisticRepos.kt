package five.head.core.domain.repos

import five.head.core.domain.model.requests_to_db.QueriesToDBModel
import five.head.core.domain.model.requests_to_db.SessionToDBModel
import five.head.core.domain.model.statistic.DatabaseInfoModel
import five.head.core.domain.model.statistic.HardwareLoadModel

interface StatisticRepos {
    suspend fun getDatabaseInfo() : Result<DatabaseInfoModel>
    suspend fun getHardwareLoad() : Result<HardwareLoadModel>
    suspend fun getQueriesToDB() : Result<List<QueriesToDBModel>>
    suspend fun getSessionsToDB(): Result<List<SessionToDBModel>>
}