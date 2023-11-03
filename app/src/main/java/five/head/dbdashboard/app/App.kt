package five.head.dbdashboard.app

import android.app.Application
import five.head.core.dependency_injection.CoreModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindProvider

class App : Application(), DIAware {
    override val di by DI.lazy {
        bindProvider { applicationContext }
        import(CoreModule)
    }

}