package five.head.core.domain.model.requests_to_db

interface SessionToDBModel {
    val userName: String
    val sessionCount: Int
    val maxQueryDuration: String
}