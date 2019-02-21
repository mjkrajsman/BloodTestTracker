package com.mjkrajsman.bloodtesttracker.model

import org.joda.time.DateTime
import java.io.Serializable

/**
 * Created by: Maciej Janusz Krajsman
 */
data class BloodTestItem(
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

//    println(map["key"])
//    map["key"] = value
    companion object {
        val abbreviationTranslator: Map<String, String> = mapOf(
            "redBloodCells" to "RBC",
            "whiteBloodCells" to "WBC",
            "platelets" to "PLT",
            "hemoglobin" to "HGB",
            "hematocrit" to "HCT",
            "meanCorpuscularVolume" to "MCV",
            "meanCorpuscularHemoglobin" to "MCH",
            "meanCorpuscularHemoglobinConcentration" to "MCHC",
            "redCellDistributionWidth" to "RDW",
            "meanPlateletVolume" to "MPV",
            "neutrophilCount" to "NE#",
            "basophilCount" to "BA#",
            "eosinophilCount" to "EO#",
            "lymphocyteCount" to "LY#",
            "monocyteCount" to "MO#",
            "neutrophilPercent" to "NE%",
            "basophilPercent" to "BA%",
            "eosinophilPercent" to "EO%",
            "lymphocytePercent" to "LY%",
            "monocytePercent" to "MO%"
        )

        fun getAbbreviation(parameter: String): String {
            return abbreviationTranslator[parameter] ?: parameter
        }
    }
}