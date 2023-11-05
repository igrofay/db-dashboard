package five.head.core.domain.repos

import five.head.core.domain.model.message_from_database.DBErrorModel

interface NotificationRepos {
    suspend fun getListDBError() : Result<List<DBErrorModel>>
}