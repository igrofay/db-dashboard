package five.head.core.data.model.statistic

import five.head.core.domain.model.statistic.HardwareLoadModel
import kotlinx.serialization.Serializable

@Serializable
internal data class HardwareLoadBody(
    override val cpuUsagePercentage: Float,
    override val availableRamMb: Float,
    override val maxRamSizeMb: Float,
    override val driveUsages: List<DriveUsagesBody>
) : HardwareLoadModel{
    @Serializable
    data class DriveUsagesBody(
        override val totalFreeSpaceGb: Float, override val totalSpaceGb: Float
    ): HardwareLoadModel.DriveUsagesModel
}