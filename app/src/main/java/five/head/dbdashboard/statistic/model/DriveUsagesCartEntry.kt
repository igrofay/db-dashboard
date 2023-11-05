package five.head.dbdashboard.statistic.model

import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.legend.legendItem
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.composed.plus
import com.patrykandpatrick.vico.core.legend.LegendItem
import five.head.core.domain.model.statistic.HardwareLoadModel
import five.head.dbdashboard.R

class DriveUsagesCartEntry(
    override val x: Float, override val y: Float
) : ChartEntry {
    override fun withY(y: Float): ChartEntry = DriveUsagesCartEntry(x, y)

    companion object {
        @Composable
        fun List<HardwareLoadModel.DriveUsagesModel>.fromListModelToChartEntry() = remember(this) {
            this.mapIndexed { index, driveUsagesModel ->
                DriveUsagesCartEntry(
                    index.toFloat(),
                    driveUsagesModel.totalSpaceGb
                )
            }.let { ChartEntryModelProducer(it) } + this.mapIndexed { index, driveUsagesModel ->
                DriveUsagesCartEntry(
                    index.toFloat(),
                    driveUsagesModel.totalSpaceGb - driveUsagesModel.totalFreeSpaceGb
                )
            }.let { ChartEntryModelProducer(it) }
        }
        internal fun axisSizeGB() : AxisValueFormatter<AxisPosition.Vertical.Start> {
            return AxisValueFormatter { value, _ ->
                "${value.toLong()} GB"
            }
        }
        @Composable
        internal fun axisNameDiskFormatter() : AxisValueFormatter<AxisPosition.Horizontal.Bottom>{
            val disk = stringResource(R.string.disk)
            return AxisValueFormatter { value, _ ->
                "$disk ${value.toInt()}"
            }
        }
        @Composable
        internal fun List<HardwareLoadModel.DriveUsagesModel>.fromModelToLegendItems() : List<LegendItem> {
            val disk = stringResource(R.string.disk)
            return this.mapIndexed{ index, model->
                val diskSizeAndBusy = stringResource(
                    R.string.disk_size_and_busy,
                    "$disk $index",
                    model.totalSpaceGb.toInt(),
                    (model.totalSpaceGb - model.totalFreeSpaceGb).toInt()
                )
                legendItem(
                    icon = shapeComponent(
                        shape = CircleShape,
                        color = MaterialTheme.colors.primary
                    ),
                    label = textComponent(
                        typeface = Typeface.SANS_SERIF
                    ),
                    labelText = diskSizeAndBusy
                )
            }
        }
    }
}