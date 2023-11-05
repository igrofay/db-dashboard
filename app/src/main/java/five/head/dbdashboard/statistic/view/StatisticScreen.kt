package five.head.dbdashboard.statistic.view

import android.graphics.Typeface
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.line.lineSpec
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.marker.markerComponent
import com.patrykandpatrick.vico.compose.component.overlayingComponent
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.legend.horizontalLegend
import com.patrykandpatrick.vico.compose.legend.legendItem
import com.patrykandpatrick.vico.compose.legend.verticalLegend
import com.patrykandpatrick.vico.core.chart.composed.plus
import com.patrykandpatrick.vico.core.entry.composed.plus
import five.head.dbdashboard.R
import five.head.dbdashboard.common.view.appbar.AppBar
import five.head.dbdashboard.common.view_model.rememberVM
import five.head.dbdashboard.statistic.model.AvailableRamMbCartEntry.Companion.axisSizeMB
import five.head.dbdashboard.statistic.model.AvailableRamMbCartEntry.Companion.axisTimeFormatter
import five.head.dbdashboard.statistic.model.AvailableRamMbCartEntry.Companion.fromListModelToChartEntry
import five.head.dbdashboard.statistic.model.DriveUsagesCartEntry.Companion.axisNameDiskFormatter
import five.head.dbdashboard.statistic.model.DriveUsagesCartEntry.Companion.axisSizeGB
import five.head.dbdashboard.statistic.model.DriveUsagesCartEntry.Companion.fromListModelToChartEntry
import five.head.dbdashboard.statistic.model.DriveUsagesCartEntry.Companion.fromModelToLegendItems
import five.head.dbdashboard.statistic.model.TableInfoCartEntry.Companion.axisBufferSize
import five.head.dbdashboard.statistic.model.TableInfoCartEntry.Companion.axisUnitNameFormatter
import five.head.dbdashboard.statistic.model.TableInfoCartEntry.Companion.fromListModelToChartEntry
import five.head.dbdashboard.statistic.view_model.StatisticVM
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun StatisticScreen() {
    val statisticVM by rememberVM<StatisticVM>()
    val state by statisticVM.collectAsState()
    Column {
        AppBar(
            icon = painterResource(R.drawable.ic_activity),
            label = stringResource(R.string.statistics)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(
                vertical = 30.dp,
            )
        ) {
            item {
                Text(
                    text = stringResource(R.string.database) +
                            " ${state.databaseInfo.databaseSizeInfo.unitName} " +
                            stringResource(R.string.with_size) +
                            " ${state.databaseInfo.databaseSizeInfo.bufferSize} " +
                            stringResource(R.string.byte1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)
                        .padding(horizontal = 14.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )
            }
            item {
                Chart(
                    chart = columnChart(
                        columns = listOf(
                            lineComponent(
                                color = Color(0xFFD35656),
                                thickness = 20.dp,
                                shape = MaterialTheme.shapes.small
                            )
                        ),
                    ),
                    model = state
                        .databaseInfo
                        .tablesInfo
                        .fromListModelToChartEntry(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 14.dp)
                        .height(200.dp),
                    bottomAxis = rememberBottomAxis(
                        valueFormatter = axisUnitNameFormatter(),
                        label = textComponent(
                            typeface = Typeface.SANS_SERIF
                        )
                    ),
                    startAxis = rememberStartAxis(
                        valueFormatter = axisBufferSize(),
                        label = textComponent(
                            typeface = Typeface.SANS_SERIF
                        )
                    ),
                    marker = markerComponent(
                        label = textComponent(
                            typeface = Typeface.SANS_SERIF
                        ),
                        indicator = overlayingComponent(
                            outer = shapeComponent(),
                            inner = shapeComponent(),
                            innerPaddingAll = 10.dp
                        ),
                        guideline = lineComponent(thickness = 0.dp, color = Color.Transparent)
                    ),
                    isZoomEnabled = true
                )
            }
            item {
                Text(
                    text = stringResource(R.string.cpu_load),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 14.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )
            }
            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 14.dp)
                        .height(130.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val animPercent by animateFloatAsState(
                        targetValue = state.transformCPUUsagePercentageToRadius,
                        label = "animPercent"
                    )
                    Canvas(
                        modifier = Modifier
                            .size(130.dp),
                    ) {
                        drawCircle(
                            color = Color.Black.copy(0.25f),
                            style = Stroke(30f)
                        )
                        drawArc(
                            color = Color(0xFFD35656),
                            style = Stroke(30f, cap = StrokeCap.Round),
                            startAngle = -90f,
                            sweepAngle = animPercent,
                            useCenter = false,
                            size = Size(size.minDimension, size.minDimension),
                        )
                    }
                    Text(
                        text = state.transformCPUUsagePercentageToString,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W500,
                    )
                }
            }
            item {
                Text(
                    text = stringResource(R.string.ram_load),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp, horizontal = 14.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )
            }
            item {
                val columnChart = columnChart(
                    columns = listOf(
                        lineComponent(
                            color = Color.Transparent,
                            thickness = 0.dp
                        )
                    )
                )
                val lineChart = lineChart(
                    lines = listOf(
                        lineSpec(
                            lineColor = Color(0xFFD35656),
                        )
                    )
                )
                val startAxis = rememberStartAxis(
                    valueFormatter = axisSizeMB(),
                    label = textComponent(
                        typeface = Typeface.SANS_SERIF
                    )
                )
                val bottomAxis = rememberBottomAxis(
                    valueFormatter = axisTimeFormatter(),
                    label = textComponent(
                        typeface = Typeface.SANS_SERIF
                    )
                )
                val chartModelProducer = remember(state.ramLoad.hashCode(), state.maxRamSizeMb) {
                    state.ramLoad.fromListModelToChartEntry() + state.maxRamSizeMBChartEntry
                }
                Chart(
                    chart = remember(columnChart, lineChart) { lineChart + columnChart },
                    chartModelProducer = chartModelProducer,
                    bottomAxis = bottomAxis,
                    startAxis = startAxis,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                        .height(200.dp),
                    runInitialAnimation = false,
                )
            }
            item {
                Text(
                    text = stringResource(R.string.persistent_memory_used),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp)
                        .padding(vertical = 20.dp, horizontal = 14.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                )
            }
            item {
                val columnChart1 = columnChart(
                    columns = listOf(
                        lineComponent(
                            color = Color(0xFFAC2727),
                            thickness = 30.dp,
                            shape = MaterialTheme.shapes.small
                        )
                    ),
                )
                val columnChart2 = columnChart(
                    columns = listOf(
                        lineComponent(
                            color = Color(0xFFD56262),
                            thickness = 30.dp,
                            shape = MaterialTheme.shapes.small
                        )
                    ),
                )
                Chart(
                    chart = remember(columnChart1, columnChart2) {
                        columnChart1 + columnChart2
                    },
                    chartModelProducer = state.driveUsages.fromListModelToChartEntry(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 14.dp)
                        .height(300.dp),
                    bottomAxis = rememberBottomAxis(
                        valueFormatter = axisNameDiskFormatter(),
                        label = textComponent(
                            typeface = Typeface.SANS_SERIF
                        )
                    ),
                    startAxis = rememberStartAxis(
                        valueFormatter = axisSizeGB(),
                        label = textComponent(
                            typeface = Typeface.SANS_SERIF
                        )
                    ),
                    isZoomEnabled = true,
                    runInitialAnimation = false,
                    legend = verticalLegend(
                        items = state.driveUsages.fromModelToLegendItems() ,
                        iconSize = 10.dp,
                        iconPadding = 10.dp
                    )
                )
            }
        }
    }
}