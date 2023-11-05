package five.head.dbdashboard.notification.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import five.head.dbdashboard.R
import five.head.dbdashboard.common.view.appbar.AppBar
import five.head.dbdashboard.common.view_model.rememberVM
import five.head.dbdashboard.notification.model.DBErrorData
import five.head.dbdashboard.notification.model.NotificationEvent
import five.head.dbdashboard.notification.view_model.NotificationVM
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotificationScreen() {
    val notificationVM by rememberVM<NotificationVM>()
    val state by notificationVM.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        AppBar(
            icon = painterResource(R.drawable.ic_notification),
            label = stringResource(R.string.notifications)
        )
        val refreshState = rememberPullRefreshState(
            refreshing = state.isRefreshing,
            onRefresh = { notificationVM.onEvent(NotificationEvent.Refresh) }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .pullRefresh(refreshState)
        ){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp)
            ){
                items(state.errors){
                    DBErrorItem(it)
                }
            }
            PullRefreshIndicator(
                state.isRefreshing,
                refreshState,
                Modifier.align(Alignment.TopCenter),
                contentColor = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
private fun DBErrorItem(
    item: DBErrorData,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = MaterialTheme.shapes.small,
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "${stringResource(R.string.error_name)}:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            SelectionContainer {
                Text(
                    text = item.message,
                    fontSize = 16.sp,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${stringResource(R.string.error_time)}:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = item.timestamp,
                fontSize = 16.sp
            )
            if (item.solutions.isNotEmpty()){
                Divider(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = "${stringResource(R.string.solutions)}:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                SelectionContainer {
                    Text(
                        text = item.solutions,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}