package five.head.dbdashboard.auth.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import five.head.dbdashboard.auth.view_model.AuthVM
import five.head.dbdashboard.common.view_model.rememberVM

@Composable
fun AuthScreen() {
    val authVM by rememberVM<AuthVM>()
    Box(
        modifier = Modifier
            .fillMaxSize()

    ){

    }
}