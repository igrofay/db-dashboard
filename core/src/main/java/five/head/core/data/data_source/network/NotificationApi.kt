package five.head.core.data.data_source.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class NotificationApi(
    private val authClient: HttpClient,
) {
    suspend fun getDBErrors() = authClient
        .get("/api/dbSettins/errors")
}