package five.head.core.data.model.statistic

import five.head.core.domain.model.statistic.DatabaseInfoModel
import kotlinx.serialization.Serializable

@Serializable
data class DatabaseInfoBody(
    override val databaseSizeInfo: DatabaseSizeInfoBody,
    override val tablesInfo: List<TablesInfoBody>
) : DatabaseInfoModel{
    @Serializable
    data class DatabaseSizeInfoBody(
        override val unitName: String, override val bufferSize: Long
    ) : DatabaseInfoModel.DatabaseSizeInfoModel
    @Serializable
    data class TablesInfoBody(
        override val unitName: String, override val bufferSize: Long
    ) : DatabaseInfoModel.TablesInfoModel
}