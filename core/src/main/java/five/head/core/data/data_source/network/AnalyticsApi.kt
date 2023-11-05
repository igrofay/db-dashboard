package five.head.core.data.data_source.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class AnalyticsApi(
    private val authClient: HttpClient,
) {
    suspend fun getDatabaseInfo() = authClient
        .get("/api/analytics/databaseInfo")
    suspend fun getHardwareLoad() = authClient
        .get("/api/analytics/hardware")
    suspend fun getQueriesToDB() = authClient
        .get("/api/analytics/queries")
    suspend fun getSessionsToDB() = authClient
        .get("/api/analytics/sessions")
}