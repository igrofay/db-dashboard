package five.head.dbdashboard.statistic.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.entriesOf
import five.head.core.domain.model.statistic.DatabaseInfoModel
import five.head.core.domain.model.statistic.HardwareLoadModel
import five.head.core.domain.utils.FixedListFIFO
import five.head.dbdashboard.common.model.mvi.UIState

data class StatisticState(
    val databaseInfo: DatabaseInfoModel = emptyDatabaseInfo,
    val cpuUsagePercentage: Float = 0f,
    val ramLoad: FixedListFIFO<Float> = FixedListFIFO(8),
    val maxRamSizeMb: Float = 0f,
    val driveUsages: List<HardwareLoadModel.DriveUsagesModel> = listOf(),
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

    val maxRamSizeMBChartEntry
        get() =  ChartEntryModelProducer(entriesOf(maxRamSizeMb))

}