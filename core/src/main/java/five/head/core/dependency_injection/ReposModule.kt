package five.head.core.dependency_injection

import five.head.core.data.repos.UserReposImpl
import five.head.core.domain.repos.UserRepos
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.new

val ReposModule by DI.Module{
    bindSingleton<UserRepos> { new(::UserReposImpl) }
}