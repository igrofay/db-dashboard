package five.head.dbdashboard.requests_to_db.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import five.head.dbdashboard.R
import five.head.dbdashboard.common.view.appbar.AppBar
import five.head.dbdashboard.common.view_model.rememberVM
import five.head.dbdashboard.requests_to_db.model.RequestsToDBEvent
import five.head.dbdashboard.requests_to_db.model.RequestsToDBState
import five.head.dbdashboard.requests_to_db.view_model.RequestsToDBViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun RequestsToDBScreen() {
    val requestsToDBViewModel by rememberVM<RequestsToDBViewModel>()
    val state by requestsToDBViewModel.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppBar(
            icon = painterResource(R.drawable.ic_request),
            label = stringResource(R.string.requests)
        )
        val pagerState = rememberPagerState { RequestsToDBState.numberOfListData }
        val refreshState = rememberPullRefreshState(
            refreshing = state.isRefreshing,
            onRefresh = { requestsToDBViewModel.onEvent(RequestsToDBEvent.Refresh) }
        )
        TabRow(
            // Our selected tab is our current page
            selectedTabIndex = pagerState.currentPage,
            // Override the indicator, using the provided pagerTabIndicatorOffset modifier
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    color = MaterialTheme.colors.primary
                )
            },
            backgroundColor = MaterialTheme.colors.background
        ) {
            val localCoroutine = rememberCoroutineScope()
            Tab(
                text = {
                    Text(
                        stringResource(R.string.requests),
                        color = MaterialTheme.colors.primary
                    )
                },
                selected = pagerState.currentPage == 0,
                onClick = { localCoroutine.launch {
                    pagerState.animateScrollToPage(0)
                } },
            )
            Tab(
                text = {
                    Text(
                        stringResource(R.string.sessions),
                        color = MaterialTheme.colors.primary,
                    )
                },
                selected = pagerState.currentPage == 1,
                onClick = { localCoroutine.launch {
                    pagerState.animateScrollToPage(1)
                } },
            )
        }
        Box(modifier = Modifier.pullRefresh(refreshState)) {
            HorizontalPager(state = pagerState) {
                when (it) {
                    0 -> QueriesToDBView(state.queries)
                    1 -> SessionsToDBView(state.sessions)
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