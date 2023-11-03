package five.head.dbdashboard.splash.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import five.head.dbdashboard.R
import five.head.dbdashboard.common.view_model.rememberVM
import five.head.dbdashboard.splash.model.SplashSideEffect
import five.head.dbdashboard.splash.view_model.SplashVM
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SplashScreen(
    goToMainContent: ()-> Unit,
    goToAuth: ()-> Unit,
) {
    val splashVM by rememberVM<SplashVM>()
    splashVM.collectSideEffect{sideEffect->
        when(sideEffect){
            SplashSideEffect.AuthorizationRequired -> goToAuth()
            SplashSideEffect.UserIsAuthorized -> goToMainContent()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        contentAlignment = Alignment.Center
    ){
        Icon(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.7f),
            tint = Color.White
        )
    }
}