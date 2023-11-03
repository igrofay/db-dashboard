package five.head.dbdashboard.nav.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import five.head.dbdashboard.common.model.route.BottomNavItem
import five.head.dbdashboard.common.view.button.scaleClick


@Composable
fun BottomNavBar(
    navController: NavController,
    contentRoutes: List<BottomNavItem>
) {
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp)
            .background(MaterialTheme.colors.primary)
            .padding(WindowInsets.navigationBars.asPaddingValues())
            .padding(vertical = 13.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (item in contentRoutes) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement
                    .spacedBy(0.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .scaleClick {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
            ) {
                Image(
                    painter = painterResource(item.icon),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = stringResource(id = item.label),
                    style = MaterialTheme.typography.caption
                        .copy(Color.White,)
                )
            }
        }
    }
}