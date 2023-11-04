package five.head.dbdashboard.statistic.model

import five.head.core.domain.model.statistic.DatabaseInfoModel
import five.head.core.domain.utils.FixedList
import five.head.dbdashboard.common.model.mvi.UIState

data class StatisticState(
    val databaseInfo: DatabaseInfoModel = emptyDatabaseInfo,
    val cpuUsagePercentage: Float = 0f,
    val ramLoad: FixedList<Float> = FixedList(15),
) : UIState() {
    companion object {
        private val emptyDatabaseInfo = object : DatabaseInfoModel {
            override val databaseSizeInfo: DatabaseInfoModel.DatabaseSizeInfoModel =
                object : DatabaseInfoModel.DatabaseSizeInfoModel {
                    override val unitName: String = ""
                    override val bufferSize: Long = 0
                }
            override val tablesInfo: List<DatabaseInfoModel.TablesInfoModel> = listOf()
        }
    }
    val transformCPUUsagePercentageToString
        get() = String.format("%.2f%%", cpuUsagePercentage)
    val transformCPUUsagePercentageToRadius
        get() = cpuUsagePercentage * 3.6f
}