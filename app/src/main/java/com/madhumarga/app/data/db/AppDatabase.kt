package com.madhumarga.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.madhumarga.app.data.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Hive::class, Inspection::class, Harvest::class, Flora::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hiveDao(): HiveDao
    abstract fun inspectionDao(): InspectionDao
    abstract fun harvestDao(): HarvestDao
    abstract fun floraDao(): FloraDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "madhumarga_database"
                )

                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database)
                }
            }
        }

        suspend fun populateDatabase(database: AppDatabase) {
            // Pre-populate flora with common Indian flowers
            val floraDao = database.floraDao()
            floraDao.insertFlora(Flora(flowerName = "Mustard (Sarson)", bloomingSeason = "Jan-Feb", nectarRating = 5, notes = "Excellent nectar source for honey bees"))
            floraDao.insertFlora(Flora(flowerName = "Litchi", bloomingSeason = "Mar-Apr", nectarRating = 5, notes = "Premium honey source"))
            floraDao.insertFlora(Flora(flowerName = "Jamun (Java Plum)", bloomingSeason = "Apr-May", nectarRating = 4, notes = "Good for summer honey flow"))
            floraDao.insertFlora(Flora(flowerName = "Sunflower", bloomingSeason = "Oct-Nov", nectarRating = 5, notes = "High pollen and nectar"))
            floraDao.insertFlora(Flora(flowerName = "Coriander", bloomingSeason = "Dec-Jan", nectarRating = 3, notes = "Moderate nectar source"))
            floraDao.insertFlora(Flora(flowerName = "Eucalyptus", bloomingSeason = "Year-round", nectarRating = 4, notes = "Consistent nectar source"))
            floraDao.insertFlora(Flora(flowerName = "Toria (Rape)", bloomingSeason = "Oct-Nov", nectarRating = 5, notes = "Major commercial nectar source"))
            floraDao.insertFlora(Flora(flowerName = "Acacia (Babool)", bloomingSeason = "Feb-Mar", nectarRating = 4, notes = "Good for light-colored honey"))
        }
    }
}
