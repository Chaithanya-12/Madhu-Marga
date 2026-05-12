package com.madhumarga.app.data.repository

import androidx.lifecycle.LiveData
import com.madhumarga.app.data.db.*
import com.madhumarga.app.data.model.*

class HiveRepository(private val hiveDao: HiveDao) {
    val allHives: LiveData<List<Hive>> = hiveDao.getAllHives()
    val hiveCount: LiveData<Int> = hiveDao.getHiveCount()
    suspend fun insert(hive: Hive) = hiveDao.insertHive(hive)
    suspend fun update(hive: Hive) = hiveDao.updateHive(hive)
    suspend fun delete(hive: Hive) = hiveDao.deleteHive(hive)
    suspend fun getHiveById(id: Int) = hiveDao.getHiveById(id)
    suspend fun getAllHivesList() = hiveDao.getAllHivesList()
}

class InspectionRepository(private val inspectionDao: InspectionDao) {
    val allInspections: LiveData<List<Inspection>> = inspectionDao.getAllInspections()
    val recentInspections: LiveData<List<Inspection>> = inspectionDao.getRecentInspections()
    val lowActivityInspections: LiveData<List<Inspection>> = inspectionDao.getLowActivityInspections()
    val inspectionCount: LiveData<Int> = inspectionDao.getInspectionCount()
    val lowActivityCount: LiveData<Int> = inspectionDao.getLowActivityCount()

    fun getInspectionsByHive(hiveId: Int) = inspectionDao.getInspectionsByHive(hiveId)
    suspend fun insert(inspection: Inspection) = inspectionDao.insertInspection(inspection)
    suspend fun delete(inspection: Inspection) = inspectionDao.deleteInspection(inspection)
}

class HarvestRepository(private val harvestDao: HarvestDao) {
    val allHarvests: LiveData<List<Harvest>> = harvestDao.getAllHarvests()
    val totalHarvest: LiveData<Float?> = harvestDao.getTotalHarvest()
    val harvestCount: LiveData<Int> = harvestDao.getHarvestCount()
    val yearlyHarvests: LiveData<List<YearlyHarvest>> = harvestDao.getYearlyHarvests()

    fun getHarvestsByHive(hiveId: Int) = harvestDao.getHarvestsByHive(hiveId)
    fun getHarvestByYear(year: String) = harvestDao.getHarvestByYear(year)
    suspend fun insert(harvest: Harvest) = harvestDao.insertHarvest(harvest)
    suspend fun delete(harvest: Harvest) = harvestDao.deleteHarvest(harvest)
}

class FloraRepository(private val floraDao: FloraDao) {
    val allFlora: LiveData<List<Flora>> = floraDao.getAllFlora()
    suspend fun insert(flora: Flora) = floraDao.insertFlora(flora)
    suspend fun delete(flora: Flora) = floraDao.deleteFlora(flora)
}
