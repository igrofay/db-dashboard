package five.head.core.dependency_injection

import android.content.Context
import five.head.core.data.data_source.database.TokenDatabase
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.kodein.di.new

private const val SharedPreferenceKey = "dash_db"

internal val DatabaseModule by DI.Module {
    bindSingleton {
        instance<Context>().getSharedPreferences(
            SharedPreferenceKey,
            Context.MODE_PRIVATE
        )
    }
    bindSingleton {
        new(::TokenDatabase)
    }
}