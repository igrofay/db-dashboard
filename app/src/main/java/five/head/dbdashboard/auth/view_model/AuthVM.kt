package five.head.dbdashboard.auth.view_model

import androidx.lifecycle.viewModelScope
import five.head.dbdashboard.auth.model.AuthEvent
import five.head.dbdashboard.auth.model.AuthSideEffect
import five.head.dbdashboard.auth.model.AuthState
import five.head.dbdashboard.common.view_model.AppVM
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.container

class AuthVM(
    override val di: DI
) : AppVM<AuthState, AuthSideEffect, AuthEvent>(), DIAware {

    override val container: Container<AuthState, AuthSideEffect> = viewModelScope
        .container(AuthState())

    override fun onEvent(event: AuthEvent) {

    }

    override fun onError(er: Throwable) {
        TODO("Not yet implemented")
    }
}