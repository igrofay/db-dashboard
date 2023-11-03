package five.head.dbdashboard.nav.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import five.head.dbdashboard.nav.model.MainContentRouting
import five.head.dbdashboard.nav.model.StartRouting

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = StartRouting.route,
    ){
        startGraph(navController)
        composable(MainContentRouting.route){
            MainContentNav(navController)
        }
    }
}