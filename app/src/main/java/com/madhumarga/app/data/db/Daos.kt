package com.madhumarga.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.madhumarga.app.data.model.*

@Dao
interface HiveDao {
    @Query("SELECT * FROM hives ORDER BY createdAt DESC")
    fun getAllHives(): LiveData<List<Hive>>
    @Query("SELECT * FROM hives ORDER BY createdAt DESC")
    suspend fun getAllHivesList(): List<Hive>
    @Query("SELECT * FROM hives WHERE id = :hiveId")
    suspend fun getHiveById(hiveId: Int): Hive?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHive(hive: Hive)
    @Update
    suspend fun updateHive(hive: Hive)
    @Delete
    suspend fun deleteHive(hive: Hive)
    @Query("SELECT COUNT(*) FROM hives")
    fun getHiveCount(): LiveData<Int>
}

@Dao
interface InspectionDao {
    @Query("SELECT * FROM inspections ORDER BY inspectionDate DESC")
    fun getAllInspections(): LiveData<List<Inspection>>

    @Query("SELECT * FROM inspections WHERE hiveId = :hiveId ORDER BY inspectionDate DESC")
    fun getInspectionsByHive(hiveId: Int): LiveData<List<Inspection>>

    @Query("SELECT * FROM inspections ORDER BY inspectionDate DESC LIMIT 10")
    fun getRecentInspections(): LiveData<List<Inspection>>

    @Query("SELECT * FROM inspections WHERE activityLevel = 'Low' ORDER BY inspectionDate DESC")
    fun getLowActivityInspections(): LiveData<List<Inspection>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInspection(inspection: Inspection)

    @Delete
    suspend fun deleteInspection(inspection: Inspection)

    @Query("SELECT COUNT(*) FROM inspections")
    fun getInspectionCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM inspections WHERE activityLevel = 'Low'")
    fun getLowActivityCount(): LiveData<Int>
}

@Dao
interface HarvestDao {
    @Query("SELECT * FROM harvests ORDER BY harvestDate DESC")
    fun getAllHarvests(): LiveData<List<Harvest>>

    @Query("SELECT * FROM harvests WHERE hiveId = :hiveId ORDER BY harvestDate DESC")
    fun getHarvestsByHive(hiveId: Int): LiveData<List<Harvest>>

    @Query("SELECT SUM(quantityKg) FROM harvests")
    fun getTotalHarvest(): LiveData<Float?>

    @Query("SELECT SUM(quantityKg) FROM harvests WHERE strftime('%Y', datetime(harvestDate/1000, 'unixepoch')) = :year")
    fun getHarvestByYear(year: String): LiveData<Float?>

    @Query("SELECT strftime('%Y', datetime(harvestDate/1000, 'unixepoch')) as year, SUM(quantityKg) as total FROM harvests GROUP BY year ORDER BY year DESC")
    fun getYearlyHarvests(): LiveData<List<YearlyHarvest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHarvest(harvest: Harvest)

    @Delete
    suspend fun deleteHarvest(harvest: Harvest)

    @Query("SELECT COUNT(*) FROM harvests")
    fun getHarvestCount(): LiveData<Int>
}

data class YearlyHarvest(val year: String, val total: Float)

@Dao
interface FloraDao {
    @Query("SELECT * FROM flora ORDER BY flowerName ASC")
    fun getAllFlora(): LiveData<List<Flora>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlora(flora: Flora)

    @Delete
    suspend fun deleteFlora(flora: Flora)
}
