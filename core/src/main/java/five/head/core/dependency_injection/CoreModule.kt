package five.head.core.dependency_injection

import org.kodein.di.DI

val CoreModule by DI.Module{
    import(DatabaseModule)
    import(ReposModule)
    import(NetworkModule)
}