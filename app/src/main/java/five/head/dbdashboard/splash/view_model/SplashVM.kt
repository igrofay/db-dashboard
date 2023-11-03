package five.head.dbdashboard.splash.view_model

import androidx.lifecycle.viewModelScope
import five.head.core.domain.repos.UserRepos
import five.head.dbdashboard.common.model.mvi.UIEvent
import five.head.dbdashboard.common.model.mvi.UIState
import five.head.dbdashboard.common.view_model.AppVM
import five.head.dbdashboard.splash.model.SplashSideEffect
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect

class SplashVM(
    override val di: DI
) : AppVM<UIState.Absence, SplashSideEffect, UIEvent.Absence>(), DIAware {
    private val userRepos by di.instance<UserRepos>()
    override val container: Container<UIState.Absence, SplashSideEffect> =
        viewModelScope.container(UIState.Absence){
            initUserState()
        }

    private fun initUserState() = intent {
        val token = userRepos.getAccessToken()
        if (token == null) {
            postSideEffect(SplashSideEffect.AuthorizationRequired)
        } else {
            postSideEffect(SplashSideEffect.UserIsAuthorized)
        }
    }


    override fun onEvent(event: UIEvent.Absence) {}


    override fun onError(er: Throwable) {

    }
}