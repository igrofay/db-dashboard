package five.head.dbdashboard.notification.model

import five.head.core.domain.model.message_from_database.DBErrorModel
import java.text.SimpleDateFormat
import java.util.Locale

data class DBErrorData(
    val timestamp: String,
    val message: String,
    val solutions: String
) {
    companion object {
        private fun formatDateString(inputDate: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
            val date = inputFormat.parse(inputDate) ?: return ""
            return outputFormat.format(date)
        }

        fun List<DBErrorModel>.fromListModelToListDBErrorData() =
            this.map {
                DBErrorData(
                    formatDateString(it.timestamp),
                    it.message,
                    solutionsToString(it.solutions)
                )
            }

        private fun solutionsToString(solutions: List<String>): String =
            solutions.mapIndexed { index, s -> "${index.inc()}. $s" }.joinToString("\n")
    }
}
