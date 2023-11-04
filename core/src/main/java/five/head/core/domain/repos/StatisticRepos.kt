package five.head.core.domain.repos

import five.head.core.domain.model.statistic.DatabaseInfoModel
import five.head.core.domain.model.statistic.HardwareLoadModel

interface StatisticRepos {
    suspend fun getDatabaseInfo() : Result<DatabaseInfoModel>
    suspend fun getHardwareLoad() : Result<HardwareLoadModel>
}