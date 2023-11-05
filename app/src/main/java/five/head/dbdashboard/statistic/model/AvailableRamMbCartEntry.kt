package five.head.dbdashboard.statistic.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import five.head.core.domain.utils.FixedListFIFO
import five.head.dbdashboard.R

class AvailableRamMbCartEntry(
    override val x: Float,
    override val y: Float,
) : ChartEntry {
    override fun withY(y: Float): ChartEntry = AvailableRamMbCartEntry(x, y)

    companion object {
        fun FixedListFIFO<Float>.fromListModelToChartEntry() = this.map(
            transform = { index, data ->
                AvailableRamMbCartEntry(
                    index.toFloat(),
                    data,
                )
            },
            transformWhenNull = { index ->
                AvailableRamMbCartEntry(index.toFloat(), 0f)
            }
        ).let { ChartEntryModelProducer(it) }
        @Composable
        internal fun axisTimeFormatter(): AxisValueFormatter<AxisPosition.Horizontal.Bottom> {
            val sec = stringResource(R.string.seconds)
            return AxisValueFormatter { value, _ ->
                when(value){
                    0f-> "${35} $sec"
                    7f -> "0 $sec"
                    else-> ""
                }
            }
        }
        internal fun axisSizeMB() : AxisValueFormatter<AxisPosition.Vertical.Start>{
            return AxisValueFormatter { value, _ ->
                "${value.toLong()} MB"
            }
        }
    }
}