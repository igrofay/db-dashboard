package five.head.dbdashboard.nav.view

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import five.head.dbdashboard.auth.view.AuthScreen
import five.head.dbdashboard.nav.model.StartRouting
import five.head.dbdashboard.splash.view.SplashScreen

fun NavGraphBuilder.startGraph(navController: NavController) {
    navigation(
        startDestination = StartRouting.Splash.route,
        route = StartRouting.route
    ) {
        composable(StartRouting.Splash.route) {
            SplashScreen(
                goToMainContent = {

                },
                goToAuth = {
                    navController.navigate(StartRouting.Auth.route){
                        popUpTo(StartRouting.Splash.route){ inclusive = true }
                    }
                }
            )
        }
        composable(StartRouting.Auth.route) {
            AuthScreen()
        }
    }
}