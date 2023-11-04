package five.head.dbdashboard.nav.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import five.head.dbdashboard.nav.model.MainContentRouting
import five.head.dbdashboard.statistic.view.StatisticScreen

@Composable
fun MainContentNav(appNavController: NavController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController,
                MainContentRouting.items
            )
        }
    ) { paddingContent ->
        NavHost(
            navController = navController,
            startDestination = MainContentRouting.Statistic.route,
            modifier = Modifier
                .padding(paddingContent)
                .fillMaxSize()
        ) {
            composable(MainContentRouting.Notification.route){

            }
            composable(MainContentRouting.Statistic.route){
                StatisticScreen()
            }
            composable(MainContentRouting.Profile.route){

            }
        }
    }
}