package five.head.dbdashboard.nav.model

import five.head.dbdashboard.R
import five.head.dbdashboard.common.model.route.AppRouting
import five.head.dbdashboard.common.model.route.BottomNavItem

sealed class MainContentRouting(route: String, override val icon: Int, override val label: Int) :
    AppRouting(route), BottomNavItem {
    data object Notification :
        MainContentRouting(
            "${route}_notification",
            R.drawable.ic_notification,
            R.string.notifications
        )

    data object Statistic :
        MainContentRouting(
            "${route}_statistic",
            R.drawable.ic_activity,
            R.string.statistics
        )

    data object Profile :
        MainContentRouting(
            "${route}_profile",
            R.drawable.ic_profile,
            R.string.profile
        )

    companion object {
        const val route = "main_content_routing"
        val items by lazy { listOf<BottomNavItem>(Notification, Statistic, Profile) }
    }
}