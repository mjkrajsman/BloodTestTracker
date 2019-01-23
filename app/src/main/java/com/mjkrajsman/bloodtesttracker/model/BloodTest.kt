package com.mjkrajsman.bloodtesttracker.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import org.joda.time.DateTime

/**
 * Created by: Maciej Janusz Krajsman
 */

@Entity(tableName = "blood_tests", foreignKeys = [ForeignKey(
    entity = Patient::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("patient_id"),
    onDelete = CASCADE)]
)
data class BloodTest(
    @NonNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int,
    @ColumnInfo(name = "patient_id") var patientId:Int,
    @NonNull @ColumnInfo(name = "test_date") var testDate: Long,
    @ColumnInfo(name = "red_blood_cells") var redBloodCells: Float?,
    @ColumnInfo(name = "white_blood_cells") var whiteBloodCells: Float?,
    @ColumnInfo(name = "platelets") var platelets: Float?,
    @ColumnInfo(name = "hemoglobin") var hemoglobin: Float?,
    @ColumnInfo(name = "hematocrit") var hematocrit: Float?,
    @ColumnInfo(name = "mean_corpuscular_volume") var meanCorpuscularVolume: Float?,
    @ColumnInfo(name = "mean_corpuscular_hemoglobin") var meanCorpuscularHemoglobin: Float?,
    @ColumnInfo(name = "mean_corpuscular_hemoglobin_concentration") var meanCorpuscularHemoglobinConcentration: Float?,
    @ColumnInfo(name = "red_cell_distribution_width") var redCellDistributionWidth: Float?,
    @ColumnInfo(name = "mean_platelet_volume") var meanPlateletVolume: Float?,
    @ColumnInfo(name = "neutrophil_count") var neutrophilCount: Float?,
    @ColumnInfo(name = "basophil_count") var basophilCount: Float?,
    @ColumnInfo(name = "eosinophil_count") var eosinophilCount: Float?,
    @ColumnInfo(name = "lymphocyte_count") var lymphocyteCount: Float?,
    @ColumnInfo(name = "basocyte_count") var basocyteCount: Float?,
    @ColumnInfo(name = "neutrophil_percent") var neutrophilPercent: Float?,
    @ColumnInfo(name = "basophil_percent") var basophilPercent: Float?,
    @ColumnInfo(name = "eosinophil_percent") var eosinophilPercent: Float?,
    @ColumnInfo(name = "lymphocyte_percent") var lymphocytePercent: Float?,
    @ColumnInfo(name = "basocyte_percent") var basocytePercent: Float?
): DateTimeMillisConverterInterface {

    //TODO: use in constructor?
    fun setTestDateToLong(dt:DateTime){
        this.testDate = dateTimeToMillis(dt)
    }

    fun getTestDateFromLong():DateTime{
        return millisToDateTime(testDate)
    }

}