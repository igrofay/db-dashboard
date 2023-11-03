package five.head.dbdashboard.splash.model

import five.head.dbdashboard.common.model.mvi.UISideEffect

sealed class SplashSideEffect : UISideEffect(){
    data object UserIsAuthorized : SplashSideEffect()
    data object AuthorizationRequired : SplashSideEffect()
}