package com.madhumarga.app.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile HiveDao _hiveDao;

  private volatile InspectionDao _inspectionDao;

  private volatile HarvestDao _harvestDao;

  private volatile FloraDao _floraDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `hives` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `location` TEXT NOT NULL, `queenPresent` INTEGER NOT NULL, `activityLevel` TEXT NOT NULL, `notes` TEXT NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `inspections` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `hiveId` INTEGER NOT NULL, `hiveName` TEXT NOT NULL, `queenSeen` INTEGER NOT NULL, `eggsPresent` INTEGER NOT NULL, `pestsSeen` INTEGER NOT NULL, `pestType` TEXT NOT NULL, `honeyFlow` TEXT NOT NULL, `activityLevel` TEXT NOT NULL, `temperature` REAL NOT NULL, `humidity` REAL NOT NULL, `notes` TEXT NOT NULL, `inspectionDate` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `harvests` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `hiveId` INTEGER NOT NULL, `hiveName` TEXT NOT NULL, `quantityKg` REAL NOT NULL, `qualityRating` INTEGER NOT NULL, `harvestDate` INTEGER NOT NULL, `notes` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `flora` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `flowerName` TEXT NOT NULL, `bloomingSeason` TEXT NOT NULL, `nectarRating` INTEGER NOT NULL, `distanceKm` REAL NOT NULL, `location` TEXT NOT NULL, `notes` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7d2003498fb303c52659651821b686eb')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `hives`");
        db.execSQL("DROP TABLE IF EXISTS `inspections`");
        db.execSQL("DROP TABLE IF EXISTS `harvests`");
        db.execSQL("DROP TABLE IF EXISTS `flora`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsHives = new HashMap<String, TableInfo.Column>(7);
        _columnsHives.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHives.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHives.put("location", new TableInfo.Column("location", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHives.put("queenPresent", new TableInfo.Column("queenPresent", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHives.put("activityLevel", new TableInfo.Column("activityLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHives.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHives.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHives = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHives = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHives = new TableInfo("hives", _columnsHives, _foreignKeysHives, _indicesHives);
        final TableInfo _existingHives = TableInfo.read(db, "hives");
        if (!_infoHives.equals(_existingHives)) {
          return new RoomOpenHelper.ValidationResult(false, "hives(com.madhumarga.app.data.model.Hive).\n"
                  + " Expected:\n" + _infoHives + "\n"
                  + " Found:\n" + _existingHives);
        }
        final HashMap<String, TableInfo.Column> _columnsInspections = new HashMap<String, TableInfo.Column>(13);
        _columnsInspections.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("hiveId", new TableInfo.Column("hiveId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("hiveName", new TableInfo.Column("hiveName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("queenSeen", new TableInfo.Column("queenSeen", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("eggsPresent", new TableInfo.Column("eggsPresent", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("pestsSeen", new TableInfo.Column("pestsSeen", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("pestType", new TableInfo.Column("pestType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("honeyFlow", new TableInfo.Column("honeyFlow", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("activityLevel", new TableInfo.Column("activityLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("temperature", new TableInfo.Column("temperature", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("humidity", new TableInfo.Column("humidity", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInspections.put("inspectionDate", new TableInfo.Column("inspectionDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInspections = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInspections = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInspections = new TableInfo("inspections", _columnsInspections, _foreignKeysInspections, _indicesInspections);
        final TableInfo _existingInspections = TableInfo.read(db, "inspections");
        if (!_infoInspections.equals(_existingInspections)) {
          return new RoomOpenHelper.ValidationResult(false, "inspections(com.madhumarga.app.data.model.Inspection).\n"
                  + " Expected:\n" + _infoInspections + "\n"
                  + " Found:\n" + _existingInspections);
        }
        final HashMap<String, TableInfo.Column> _columnsHarvests = new HashMap<String, TableInfo.Column>(7);
        _columnsHarvests.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvests.put("hiveId", new TableInfo.Column("hiveId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvests.put("hiveName", new TableInfo.Column("hiveName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvests.put("quantityKg", new TableInfo.Column("quantityKg", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvests.put("qualityRating", new TableInfo.Column("qualityRating", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvests.put("harvestDate", new TableInfo.Column("harvestDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsHarvests.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHarvests = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHarvests = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHarvests = new TableInfo("harvests", _columnsHarvests, _foreignKeysHarvests, _indicesHarvests);
        final TableInfo _existingHarvests = TableInfo.read(db, "harvests");
        if (!_infoHarvests.equals(_existingHarvests)) {
          return new RoomOpenHelper.ValidationResult(false, "harvests(com.madhumarga.app.data.model.Harvest).\n"
                  + " Expected:\n" + _infoHarvests + "\n"
                  + " Found:\n" + _existingHarvests);
        }
        final HashMap<String, TableInfo.Column> _columnsFlora = new HashMap<String, TableInfo.Column>(7);
        _columnsFlora.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlora.put("flowerName", new TableInfo.Column("flowerName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlora.put("bloomingSeason", new TableInfo.Column("bloomingSeason", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlora.put("nectarRating", new TableInfo.Column("nectarRating", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlora.put("distanceKm", new TableInfo.Column("distanceKm", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlora.put("location", new TableInfo.Column("location", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFlora.put("notes", new TableInfo.Column("notes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFlora = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFlora = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFlora = new TableInfo("flora", _columnsFlora, _foreignKeysFlora, _indicesFlora);
        final TableInfo _existingFlora = TableInfo.read(db, "flora");
        if (!_infoFlora.equals(_existingFlora)) {
          return new RoomOpenHelper.ValidationResult(false, "flora(com.madhumarga.app.data.model.Flora).\n"
                  + " Expected:\n" + _infoFlora + "\n"
                  + " Found:\n" + _existingFlora);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "7d2003498fb303c52659651821b686eb", "c9866163485c5a1e0bb17ee56a79930d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "hives","inspections","harvests","flora");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `hives`");
      _db.execSQL("DELETE FROM `inspections`");
      _db.execSQL("DELETE FROM `harvests`");
      _db.execSQL("DELETE FROM `flora`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(HiveDao.class, HiveDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(InspectionDao.class, InspectionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(HarvestDao.class, HarvestDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FloraDao.class, FloraDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public HiveDao hiveDao() {
    if (_hiveDao != null) {
      return _hiveDao;
    } else {
      synchronized(this) {
        if(_hiveDao == null) {
          _hiveDao = new HiveDao_Impl(this);
        }
        return _hiveDao;
      }
    }
  }

  @Override
  public InspectionDao inspectionDao() {
    if (_inspectionDao != null) {
      return _inspectionDao;
    } else {
      synchronized(this) {
        if(_inspectionDao == null) {
          _inspectionDao = new InspectionDao_Impl(this);
        }
        return _inspectionDao;
      }
    }
  }

  @Override
  public HarvestDao harvestDao() {
    if (_harvestDao != null) {
      return _harvestDao;
    } else {
      synchronized(this) {
        if(_harvestDao == null) {
          _harvestDao = new HarvestDao_Impl(this);
        }
        return _harvestDao;
      }
    }
  }

  @Override
  public FloraDao floraDao() {
    if (_floraDao != null) {
      return _floraDao;
    } else {
      synchronized(this) {
        if(_floraDao == null) {
          _floraDao = new FloraDao_Impl(this);
        }
        return _floraDao;
      }
    }
  }
}
