package com.madhumarga.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hives")
data class Hive(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val location: String,
    val queenPresent: Boolean = true,
    val activityLevel: String = "Normal", // Low, Normal, High
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "inspections")
data class Inspection(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hiveId: Int,
    val hiveName: String,
    val queenSeen: Boolean,
    val eggsPresent: Boolean,
    val pestsSeen: Boolean,
    val pestType: String = "",
    val honeyFlow: String = "Normal", // Low, Normal, High
    val activityLevel: String = "Normal", // Low, Normal, High
    val temperature: Float = 0f,
    val humidity: Float = 0f,
    val notes: String = "",
    val inspectionDate: Long = System.currentTimeMillis()
)

@Entity(tableName = "harvests")
data class Harvest(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hiveId: Int,
    val hiveName: String,
    val quantityKg: Float,
    val qualityRating: Int = 5, // 1-5
    val harvestDate: Long = System.currentTimeMillis(),
    val notes: String = ""
)

@Entity(tableName = "flora")
data class Flora(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val flowerName: String,
    val bloomingSeason: String,
    val nectarRating: Int = 3, // 1-5
    val distanceKm: Float = 0f,
    val location: String = "",
    val notes: String = ""
)
