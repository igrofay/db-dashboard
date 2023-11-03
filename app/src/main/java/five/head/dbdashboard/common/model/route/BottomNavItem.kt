package five.head.dbdashboard.common.model.route

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface BottomNavItem {
    val route: String
    @get:DrawableRes
    val icon: Int
    @get:StringRes
    val label: Int
}