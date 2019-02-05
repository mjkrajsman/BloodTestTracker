package com.mjkrajsman.bloodtesttracker.model

import org.joda.time.DateTime
import java.io.Serializable

/**
 * Created by: Maciej Janusz Krajsman
 */
class BloodTestItem(
    val id: Int,
    val patientId: Int,
    val testDate: DateTime,
    val redBloodCells: Float?,
    val whiteBloodCells: Float?,
    val platelets: Float?,
    val hemoglobin: Float?,
    val hematocrit: Float?,
    val meanCorpuscularVolume: Float?,
    val meanCorpuscularHemoglobin: Float?,
    val meanCorpuscularHemoglobinConcentration: Float?,
    val redCellDistributionWidth: Float?,
    val meanPlateletVolume: Float?,
    val neutrophilCount: Float?,
    val basophilCount: Float?,
    val eosinophilCount: Float?,
    val lymphocyteCount: Float?,
    val monocyteCount: Float?,
    val neutrophilPercent: Float?,
    val basophilPercent: Float?,
    val eosinophilPercent: Float?,
    val lymphocytePercent: Float?,
    val monocytePercent: Float?
): Serializable {
    constructor(bloodTest: BloodTest): this(
        id = bloodTest.id,
        patientId = bloodTest.patientId,
        testDate = bloodTest.getTestDateFromLong(),
        redBloodCells = bloodTest.redBloodCells,
        whiteBloodCells = bloodTest.whiteBloodCells,
        platelets = bloodTest.platelets,
        hemoglobin = bloodTest.hemoglobin,
        hematocrit = bloodTest.hematocrit,
        meanCorpuscularVolume = bloodTest.meanCorpuscularVolume,
        meanCorpuscularHemoglobin = bloodTest.meanCorpuscularHemoglobin,
        meanCorpuscularHemoglobinConcentration = bloodTest.meanCorpuscularHemoglobinConcentration,
        redCellDistributionWidth = bloodTest.redCellDistributionWidth,
        meanPlateletVolume = bloodTest.meanPlateletVolume,
        neutrophilCount = bloodTest.neutrophilCount,
        basophilCount = bloodTest.basophilCount,
        eosinophilCount = bloodTest.eosinophilCount,
        lymphocyteCount = bloodTest.lymphocyteCount,
        monocyteCount = bloodTest.monocyteCount,
        neutrophilPercent = bloodTest.neutrophilPercent,
        basophilPercent = bloodTest.basophilPercent,
        eosinophilPercent = bloodTest.eosinophilPercent,
        lymphocytePercent = bloodTest.lymphocytePercent,
        monocytePercent = bloodTest.monocytePercent
    )
}