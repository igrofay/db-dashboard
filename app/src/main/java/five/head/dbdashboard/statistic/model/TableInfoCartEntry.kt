package five.head.dbdashboard.statistic.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import five.head.core.domain.model.statistic.DatabaseInfoModel
import five.head.dbdashboard.R

class TableInfoCartEntry(
    val unitName: String,
    override val x: Float,
    override val y: Float
) : ChartEntry {
    override fun withY(y: Float) = TableInfoCartEntry(unitName, x, y)

    companion object {
        fun List<DatabaseInfoModel.TablesInfoModel>.fromListModelToChartEntry() =
            this.mapIndexed { index, tablesInfoModel ->
                TableInfoCartEntry(
                    tablesInfoModel.unitName,
                    index.toFloat(),
                    tablesInfoModel.bufferSize.toFloat()
                )
            }.let { ChartEntryModelProducer(it) }.getModel()

        @Composable
        internal fun axisUnitNameFormatter(): AxisValueFormatter<AxisPosition.Horizontal.Bottom> {
            val table = stringResource(R.string.table)
            return AxisValueFormatter { value, chartValues ->
                (chartValues.chartEntryModel.entries.first()
                    .getOrNull(value.toInt()) as? TableInfoCartEntry)
                    ?.unitName
                    .orEmpty() + " $table"
            }
        }
        @Composable
        internal fun axisBufferSize() : AxisValueFormatter<AxisPosition.Vertical.Start>{
            val byte = stringResource(R.string.byte1)
            return AxisValueFormatter { value, _ ->
                "${value.toLong()} $byte"
            }
        }
    }
}