package five.head.dbdashboard.common.view.theme

import android.app.Activity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat

private val colorsThemeDefault = lightColors(
    background = Color.White,
    primary = primary
)

@Composable
fun AppTheme(
    content: @Composable ()-> Unit
){
    val activity = LocalContext.current as? Activity
    SideEffect {
        activity?.window?.let {window->
            WindowCompat
                .getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false // !isDark
        }
    }
    MaterialTheme(
        colors = colorsThemeDefault,
        shapes = ShapesDefault,
        typography = TypographyDefault,
        content = content,
    )
}
