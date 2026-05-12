package com.madhumarga.app.data.db;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.madhumarga.app.data.model.Harvest;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HarvestDao_Impl implements HarvestDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Harvest> __insertionAdapterOfHarvest;

  private final EntityDeletionOrUpdateAdapter<Harvest> __deletionAdapterOfHarvest;

  public HarvestDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHarvest = new EntityInsertionAdapter<Harvest>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `harvests` (`id`,`hiveId`,`hiveName`,`quantityKg`,`qualityRating`,`harvestDate`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Harvest entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getHiveId());
        statement.bindString(3, entity.getHiveName());
        statement.bindDouble(4, entity.getQuantityKg());
        statement.bindLong(5, entity.getQualityRating());
        statement.bindLong(6, entity.getHarvestDate());
        statement.bindString(7, entity.getNotes());
      }
    };
    this.__deletionAdapterOfHarvest = new EntityDeletionOrUpdateAdapter<Harvest>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `harvests` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Harvest entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertHarvest(final Harvest harvest, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHarvest.insert(harvest);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteHarvest(final Harvest harvest, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfHarvest.handle(harvest);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Harvest>> getAllHarvests() {
    final String _sql = "SELECT * FROM harvests ORDER BY harvestDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"harvests"}, false, new Callable<List<Harvest>>() {
      @Override
      @Nullable
      public List<Harvest> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHiveId = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveId");
          final int _cursorIndexOfHiveName = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveName");
          final int _cursorIndexOfQuantityKg = CursorUtil.getColumnIndexOrThrow(_cursor, "quantityKg");
          final int _cursorIndexOfQualityRating = CursorUtil.getColumnIndexOrThrow(_cursor, "qualityRating");
          final int _cursorIndexOfHarvestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<Harvest> _result = new ArrayList<Harvest>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Harvest _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpHiveId;
            _tmpHiveId = _cursor.getInt(_cursorIndexOfHiveId);
            final String _tmpHiveName;
            _tmpHiveName = _cursor.getString(_cursorIndexOfHiveName);
            final float _tmpQuantityKg;
            _tmpQuantityKg = _cursor.getFloat(_cursorIndexOfQuantityKg);
            final int _tmpQualityRating;
            _tmpQualityRating = _cursor.getInt(_cursorIndexOfQualityRating);
            final long _tmpHarvestDate;
            _tmpHarvestDate = _cursor.getLong(_cursorIndexOfHarvestDate);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new Harvest(_tmpId,_tmpHiveId,_tmpHiveName,_tmpQuantityKg,_tmpQualityRating,_tmpHarvestDate,_tmpNotes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Harvest>> getHarvestsByHive(final int hiveId) {
    final String _sql = "SELECT * FROM harvests WHERE hiveId = ? ORDER BY harvestDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, hiveId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"harvests"}, false, new Callable<List<Harvest>>() {
      @Override
      @Nullable
      public List<Harvest> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHiveId = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveId");
          final int _cursorIndexOfHiveName = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveName");
          final int _cursorIndexOfQuantityKg = CursorUtil.getColumnIndexOrThrow(_cursor, "quantityKg");
          final int _cursorIndexOfQualityRating = CursorUtil.getColumnIndexOrThrow(_cursor, "qualityRating");
          final int _cursorIndexOfHarvestDate = CursorUtil.getColumnIndexOrThrow(_cursor, "harvestDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<Harvest> _result = new ArrayList<Harvest>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Harvest _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpHiveId;
            _tmpHiveId = _cursor.getInt(_cursorIndexOfHiveId);
            final String _tmpHiveName;
            _tmpHiveName = _cursor.getString(_cursorIndexOfHiveName);
            final float _tmpQuantityKg;
            _tmpQuantityKg = _cursor.getFloat(_cursorIndexOfQuantityKg);
            final int _tmpQualityRating;
            _tmpQualityRating = _cursor.getInt(_cursorIndexOfQualityRating);
            final long _tmpHarvestDate;
            _tmpHarvestDate = _cursor.getLong(_cursorIndexOfHarvestDate);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new Harvest(_tmpId,_tmpHiveId,_tmpHiveName,_tmpQuantityKg,_tmpQualityRating,_tmpHarvestDate,_tmpNotes);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Float> getTotalHarvest() {
    final String _sql = "SELECT SUM(quantityKg) FROM harvests";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"harvests"}, false, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Float> getHarvestByYear(final String year) {
    final String _sql = "SELECT SUM(quantityKg) FROM harvests WHERE strftime('%Y', datetime(harvestDate/1000, 'unixepoch')) = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, year);
    return __db.getInvalidationTracker().createLiveData(new String[] {"harvests"}, false, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<YearlyHarvest>> getYearlyHarvests() {
    final String _sql = "SELECT strftime('%Y', datetime(harvestDate/1000, 'unixepoch')) as year, SUM(quantityKg) as total FROM harvests GROUP BY year ORDER BY year DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"harvests"}, false, new Callable<List<YearlyHarvest>>() {
      @Override
      @Nullable
      public List<YearlyHarvest> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfYear = 0;
          final int _cursorIndexOfTotal = 1;
          final List<YearlyHarvest> _result = new ArrayList<YearlyHarvest>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final YearlyHarvest _item;
            final String _tmpYear;
            _tmpYear = _cursor.getString(_cursorIndexOfYear);
            final float _tmpTotal;
            _tmpTotal = _cursor.getFloat(_cursorIndexOfTotal);
            _item = new YearlyHarvest(_tmpYear,_tmpTotal);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Integer> getHarvestCount() {
    final String _sql = "SELECT COUNT(*) FROM harvests";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"harvests"}, false, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
