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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.component.lineComponent
import com.patrykandpatrick.vico.compose.component.marker.markerComponent
import com.patrykandpatrick.vico.compose.component.overlayingComponent
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import five.head.dbdashboard.R
import five.head.dbdashboard.common.view.appbar.AppBar
import five.head.dbdashboard.common.view_model.rememberVM
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
                    )
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
                            color = Color.Black.copy(0.3f),
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

        }
    }
}