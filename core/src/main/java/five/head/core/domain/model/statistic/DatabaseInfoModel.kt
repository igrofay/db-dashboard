package five.head.core.domain.model.statistic

interface DatabaseInfoModel {
    val databaseSizeInfo: DatabaseSizeInfoModel
    val tablesInfo: List<TablesInfoModel>
    interface DatabaseSizeInfoModel{
        val unitName: String
        val bufferSize: Long
    }
    interface TablesInfoModel{
        val unitName: String
        val bufferSize: Long
    }
}