package five.head.core.domain.model.statistic

interface HardwareLoadModel {
    val cpuUsagePercentage: Float
    val availableRamMb: Float
    val maxRamSizeMb: Float
    val driveUsages: List<DriveUsagesModel>
    interface DriveUsagesModel{
        val totalFreeSpaceGb: Float
        val totalSpaceGb: Float
    }
}