package five.head.dbdashboard.requests_to_db.view

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import five.head.core.domain.model.requests_to_db.QueriesToDBModel
import five.head.dbdashboard.R

@Composable
fun QueriesToDBView(
    queries: List<QueriesToDBModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 15.dp)
    ){
        items(queries){
            QueriesToDBItem(item = it)
        }
    }
}

@Composable
private fun QueriesToDBItem(
    item: QueriesToDBModel
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 2.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "${stringResource(R.string.query_text)}:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            SelectionContainer {
                Text(
                    text = item.queryText,
                    fontSize = 16.sp,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${stringResource(R.string.query_count)}:",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = item.queryCount.toString(),
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${stringResource(R.string.max_query_duration)}:",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.maxQueryDuration.replace("-", ""),
                fontSize = 16.sp
            )
        }
    }
}

