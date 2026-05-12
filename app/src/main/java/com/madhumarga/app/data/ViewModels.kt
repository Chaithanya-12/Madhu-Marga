package com.madhumarga.app.data

import android.app.Application
import androidx.lifecycle.*
import com.madhumarga.app.data.db.AppDatabase
import com.madhumarga.app.data.model.*
import com.madhumarga.app.data.repository.*
import kotlinx.coroutines.launch

class HiveViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: HiveRepository
    val allHives: LiveData<List<Hive>>
    val hiveCount: LiveData<Int>

    init {
        val dao = AppDatabase.getDatabase(application).hiveDao()
        repo = HiveRepository(dao)
        allHives = repo.allHives
        hiveCount = repo.hiveCount
    }

    fun insert(hive: Hive) = viewModelScope.launch { repo.insert(hive) }
    fun update(hive: Hive) = viewModelScope.launch { repo.update(hive) }
    fun delete(hive: Hive) = viewModelScope.launch { repo.delete(hive) }
    suspend fun getHiveById(id: Int) = repo.getHiveById(id)
    suspend fun getAllHivesList() = repo.getAllHivesList()
}

class InspectionViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: InspectionRepository
    val allInspections: LiveData<List<Inspection>>
    val recentInspections: LiveData<List<Inspection>>
    val lowActivityInspections: LiveData<List<Inspection>>
    val inspectionCount: LiveData<Int>
    val lowActivityCount: LiveData<Int>

    init {
        val dao = AppDatabase.getDatabase(application).inspectionDao()
        repo = InspectionRepository(dao)
        allInspections = repo.allInspections
        recentInspections = repo.recentInspections
        lowActivityInspections = repo.lowActivityInspections
        inspectionCount = repo.inspectionCount
        lowActivityCount = repo.lowActivityCount
    }

    fun getInspectionsByHive(hiveId: Int) = repo.getInspectionsByHive(hiveId)
    fun insert(inspection: Inspection) = viewModelScope.launch { repo.insert(inspection) }
    fun delete(inspection: Inspection) = viewModelScope.launch { repo.delete(inspection) }
}

class HarvestViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: HarvestRepository
    val allHarvests: LiveData<List<Harvest>>
    val totalHarvest: LiveData<Float?>
    val harvestCount: LiveData<Int>
    val yearlyHarvests: LiveData<List<com.madhumarga.app.data.db.YearlyHarvest>>

    init {
        val dao = AppDatabase.getDatabase(application).harvestDao()
        repo = HarvestRepository(dao)
        allHarvests = repo.allHarvests
        totalHarvest = repo.totalHarvest
        harvestCount = repo.harvestCount
        yearlyHarvests = repo.yearlyHarvests
    }

    fun getHarvestsByHive(hiveId: Int) = repo.getHarvestsByHive(hiveId)
    fun getHarvestByYear(year: String) = repo.getHarvestByYear(year)
    fun insert(harvest: Harvest) = viewModelScope.launch { repo.insert(harvest) }
    fun delete(harvest: Harvest) = viewModelScope.launch { repo.delete(harvest) }
}

class FloraViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: FloraRepository
    val allFlora: LiveData<List<Flora>>

    init {
        val dao = AppDatabase.getDatabase(application).floraDao()
        repo = FloraRepository(dao)
        allFlora = repo.allFlora
    }

    fun insert(flora: Flora) = viewModelScope.launch { repo.insert(flora) }
    fun delete(flora: Flora) = viewModelScope.launch { repo.delete(flora) }
}
