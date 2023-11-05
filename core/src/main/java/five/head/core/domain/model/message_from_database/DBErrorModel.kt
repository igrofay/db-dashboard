package five.head.core.domain.model.message_from_database

interface DBErrorModel {
    val timestamp: String
    val message: String
    val solutions: List<String>
}