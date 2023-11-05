package five.head.core.data.repos

import five.head.core.data.data_source.network.NotificationApi
import five.head.core.data.model.message_from_database.DBErrorBody
import five.head.core.domain.model.message_from_database.DBErrorModel
import five.head.core.domain.repos.NotificationRepos
import io.ktor.client.call.body

internal class NotificationReposImpl(
    private val notificationApi: NotificationApi,
) : NotificationRepos {
    override suspend fun getListDBError(): Result<List<DBErrorModel>> =
        runCatching { notificationApi.getDBErrors().body<List<DBErrorBody>>() }
}