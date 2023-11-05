package five.head.core.domain.model.requests_to_db

interface QueriesToDBModel {
    val queryText: String
    val queryCount: Int
    val maxQueryDuration: String
}