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
import com.madhumarga.app.data.model.Inspection;
import java.lang.Class;
import java.lang.Exception;
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
public final class InspectionDao_Impl implements InspectionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Inspection> __insertionAdapterOfInspection;

  private final EntityDeletionOrUpdateAdapter<Inspection> __deletionAdapterOfInspection;

  public InspectionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInspection = new EntityInsertionAdapter<Inspection>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `inspections` (`id`,`hiveId`,`hiveName`,`queenSeen`,`eggsPresent`,`pestsSeen`,`pestType`,`honeyFlow`,`activityLevel`,`temperature`,`humidity`,`notes`,`inspectionDate`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Inspection entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getHiveId());
        statement.bindString(3, entity.getHiveName());
        final int _tmp = entity.getQueenSeen() ? 1 : 0;
        statement.bindLong(4, _tmp);
        final int _tmp_1 = entity.getEggsPresent() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        final int _tmp_2 = entity.getPestsSeen() ? 1 : 0;
        statement.bindLong(6, _tmp_2);
        statement.bindString(7, entity.getPestType());
        statement.bindString(8, entity.getHoneyFlow());
        statement.bindString(9, entity.getActivityLevel());
        statement.bindDouble(10, entity.getTemperature());
        statement.bindDouble(11, entity.getHumidity());
        statement.bindString(12, entity.getNotes());
        statement.bindLong(13, entity.getInspectionDate());
      }
    };
    this.__deletionAdapterOfInspection = new EntityDeletionOrUpdateAdapter<Inspection>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `inspections` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Inspection entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertInspection(final Inspection inspection,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfInspection.insert(inspection);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteInspection(final Inspection inspection,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfInspection.handle(inspection);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Inspection>> getAllInspections() {
    final String _sql = "SELECT * FROM inspections ORDER BY inspectionDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"inspections"}, false, new Callable<List<Inspection>>() {
      @Override
      @Nullable
      public List<Inspection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHiveId = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveId");
          final int _cursorIndexOfHiveName = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveName");
          final int _cursorIndexOfQueenSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "queenSeen");
          final int _cursorIndexOfEggsPresent = CursorUtil.getColumnIndexOrThrow(_cursor, "eggsPresent");
          final int _cursorIndexOfPestsSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "pestsSeen");
          final int _cursorIndexOfPestType = CursorUtil.getColumnIndexOrThrow(_cursor, "pestType");
          final int _cursorIndexOfHoneyFlow = CursorUtil.getColumnIndexOrThrow(_cursor, "honeyFlow");
          final int _cursorIndexOfActivityLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "activityLevel");
          final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfInspectionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "inspectionDate");
          final List<Inspection> _result = new ArrayList<Inspection>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Inspection _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpHiveId;
            _tmpHiveId = _cursor.getInt(_cursorIndexOfHiveId);
            final String _tmpHiveName;
            _tmpHiveName = _cursor.getString(_cursorIndexOfHiveName);
            final boolean _tmpQueenSeen;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfQueenSeen);
            _tmpQueenSeen = _tmp != 0;
            final boolean _tmpEggsPresent;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEggsPresent);
            _tmpEggsPresent = _tmp_1 != 0;
            final boolean _tmpPestsSeen;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfPestsSeen);
            _tmpPestsSeen = _tmp_2 != 0;
            final String _tmpPestType;
            _tmpPestType = _cursor.getString(_cursorIndexOfPestType);
            final String _tmpHoneyFlow;
            _tmpHoneyFlow = _cursor.getString(_cursorIndexOfHoneyFlow);
            final String _tmpActivityLevel;
            _tmpActivityLevel = _cursor.getString(_cursorIndexOfActivityLevel);
            final float _tmpTemperature;
            _tmpTemperature = _cursor.getFloat(_cursorIndexOfTemperature);
            final float _tmpHumidity;
            _tmpHumidity = _cursor.getFloat(_cursorIndexOfHumidity);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            final long _tmpInspectionDate;
            _tmpInspectionDate = _cursor.getLong(_cursorIndexOfInspectionDate);
            _item = new Inspection(_tmpId,_tmpHiveId,_tmpHiveName,_tmpQueenSeen,_tmpEggsPresent,_tmpPestsSeen,_tmpPestType,_tmpHoneyFlow,_tmpActivityLevel,_tmpTemperature,_tmpHumidity,_tmpNotes,_tmpInspectionDate);
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
  public LiveData<List<Inspection>> getInspectionsByHive(final int hiveId) {
    final String _sql = "SELECT * FROM inspections WHERE hiveId = ? ORDER BY inspectionDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, hiveId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"inspections"}, false, new Callable<List<Inspection>>() {
      @Override
      @Nullable
      public List<Inspection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHiveId = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveId");
          final int _cursorIndexOfHiveName = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveName");
          final int _cursorIndexOfQueenSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "queenSeen");
          final int _cursorIndexOfEggsPresent = CursorUtil.getColumnIndexOrThrow(_cursor, "eggsPresent");
          final int _cursorIndexOfPestsSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "pestsSeen");
          final int _cursorIndexOfPestType = CursorUtil.getColumnIndexOrThrow(_cursor, "pestType");
          final int _cursorIndexOfHoneyFlow = CursorUtil.getColumnIndexOrThrow(_cursor, "honeyFlow");
          final int _cursorIndexOfActivityLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "activityLevel");
          final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfInspectionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "inspectionDate");
          final List<Inspection> _result = new ArrayList<Inspection>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Inspection _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpHiveId;
            _tmpHiveId = _cursor.getInt(_cursorIndexOfHiveId);
            final String _tmpHiveName;
            _tmpHiveName = _cursor.getString(_cursorIndexOfHiveName);
            final boolean _tmpQueenSeen;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfQueenSeen);
            _tmpQueenSeen = _tmp != 0;
            final boolean _tmpEggsPresent;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEggsPresent);
            _tmpEggsPresent = _tmp_1 != 0;
            final boolean _tmpPestsSeen;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfPestsSeen);
            _tmpPestsSeen = _tmp_2 != 0;
            final String _tmpPestType;
            _tmpPestType = _cursor.getString(_cursorIndexOfPestType);
            final String _tmpHoneyFlow;
            _tmpHoneyFlow = _cursor.getString(_cursorIndexOfHoneyFlow);
            final String _tmpActivityLevel;
            _tmpActivityLevel = _cursor.getString(_cursorIndexOfActivityLevel);
            final float _tmpTemperature;
            _tmpTemperature = _cursor.getFloat(_cursorIndexOfTemperature);
            final float _tmpHumidity;
            _tmpHumidity = _cursor.getFloat(_cursorIndexOfHumidity);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            final long _tmpInspectionDate;
            _tmpInspectionDate = _cursor.getLong(_cursorIndexOfInspectionDate);
            _item = new Inspection(_tmpId,_tmpHiveId,_tmpHiveName,_tmpQueenSeen,_tmpEggsPresent,_tmpPestsSeen,_tmpPestType,_tmpHoneyFlow,_tmpActivityLevel,_tmpTemperature,_tmpHumidity,_tmpNotes,_tmpInspectionDate);
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
  public LiveData<List<Inspection>> getRecentInspections() {
    final String _sql = "SELECT * FROM inspections ORDER BY inspectionDate DESC LIMIT 10";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"inspections"}, false, new Callable<List<Inspection>>() {
      @Override
      @Nullable
      public List<Inspection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHiveId = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveId");
          final int _cursorIndexOfHiveName = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveName");
          final int _cursorIndexOfQueenSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "queenSeen");
          final int _cursorIndexOfEggsPresent = CursorUtil.getColumnIndexOrThrow(_cursor, "eggsPresent");
          final int _cursorIndexOfPestsSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "pestsSeen");
          final int _cursorIndexOfPestType = CursorUtil.getColumnIndexOrThrow(_cursor, "pestType");
          final int _cursorIndexOfHoneyFlow = CursorUtil.getColumnIndexOrThrow(_cursor, "honeyFlow");
          final int _cursorIndexOfActivityLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "activityLevel");
          final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfInspectionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "inspectionDate");
          final List<Inspection> _result = new ArrayList<Inspection>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Inspection _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpHiveId;
            _tmpHiveId = _cursor.getInt(_cursorIndexOfHiveId);
            final String _tmpHiveName;
            _tmpHiveName = _cursor.getString(_cursorIndexOfHiveName);
            final boolean _tmpQueenSeen;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfQueenSeen);
            _tmpQueenSeen = _tmp != 0;
            final boolean _tmpEggsPresent;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEggsPresent);
            _tmpEggsPresent = _tmp_1 != 0;
            final boolean _tmpPestsSeen;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfPestsSeen);
            _tmpPestsSeen = _tmp_2 != 0;
            final String _tmpPestType;
            _tmpPestType = _cursor.getString(_cursorIndexOfPestType);
            final String _tmpHoneyFlow;
            _tmpHoneyFlow = _cursor.getString(_cursorIndexOfHoneyFlow);
            final String _tmpActivityLevel;
            _tmpActivityLevel = _cursor.getString(_cursorIndexOfActivityLevel);
            final float _tmpTemperature;
            _tmpTemperature = _cursor.getFloat(_cursorIndexOfTemperature);
            final float _tmpHumidity;
            _tmpHumidity = _cursor.getFloat(_cursorIndexOfHumidity);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            final long _tmpInspectionDate;
            _tmpInspectionDate = _cursor.getLong(_cursorIndexOfInspectionDate);
            _item = new Inspection(_tmpId,_tmpHiveId,_tmpHiveName,_tmpQueenSeen,_tmpEggsPresent,_tmpPestsSeen,_tmpPestType,_tmpHoneyFlow,_tmpActivityLevel,_tmpTemperature,_tmpHumidity,_tmpNotes,_tmpInspectionDate);
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
  public LiveData<List<Inspection>> getLowActivityInspections() {
    final String _sql = "SELECT * FROM inspections WHERE activityLevel = 'Low' ORDER BY inspectionDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"inspections"}, false, new Callable<List<Inspection>>() {
      @Override
      @Nullable
      public List<Inspection> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHiveId = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveId");
          final int _cursorIndexOfHiveName = CursorUtil.getColumnIndexOrThrow(_cursor, "hiveName");
          final int _cursorIndexOfQueenSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "queenSeen");
          final int _cursorIndexOfEggsPresent = CursorUtil.getColumnIndexOrThrow(_cursor, "eggsPresent");
          final int _cursorIndexOfPestsSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "pestsSeen");
          final int _cursorIndexOfPestType = CursorUtil.getColumnIndexOrThrow(_cursor, "pestType");
          final int _cursorIndexOfHoneyFlow = CursorUtil.getColumnIndexOrThrow(_cursor, "honeyFlow");
          final int _cursorIndexOfActivityLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "activityLevel");
          final int _cursorIndexOfTemperature = CursorUtil.getColumnIndexOrThrow(_cursor, "temperature");
          final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfInspectionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "inspectionDate");
          final List<Inspection> _result = new ArrayList<Inspection>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Inspection _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpHiveId;
            _tmpHiveId = _cursor.getInt(_cursorIndexOfHiveId);
            final String _tmpHiveName;
            _tmpHiveName = _cursor.getString(_cursorIndexOfHiveName);
            final boolean _tmpQueenSeen;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfQueenSeen);
            _tmpQueenSeen = _tmp != 0;
            final boolean _tmpEggsPresent;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfEggsPresent);
            _tmpEggsPresent = _tmp_1 != 0;
            final boolean _tmpPestsSeen;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfPestsSeen);
            _tmpPestsSeen = _tmp_2 != 0;
            final String _tmpPestType;
            _tmpPestType = _cursor.getString(_cursorIndexOfPestType);
            final String _tmpHoneyFlow;
            _tmpHoneyFlow = _cursor.getString(_cursorIndexOfHoneyFlow);
            final String _tmpActivityLevel;
            _tmpActivityLevel = _cursor.getString(_cursorIndexOfActivityLevel);
            final float _tmpTemperature;
            _tmpTemperature = _cursor.getFloat(_cursorIndexOfTemperature);
            final float _tmpHumidity;
            _tmpHumidity = _cursor.getFloat(_cursorIndexOfHumidity);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            final long _tmpInspectionDate;
            _tmpInspectionDate = _cursor.getLong(_cursorIndexOfInspectionDate);
            _item = new Inspection(_tmpId,_tmpHiveId,_tmpHiveName,_tmpQueenSeen,_tmpEggsPresent,_tmpPestsSeen,_tmpPestType,_tmpHoneyFlow,_tmpActivityLevel,_tmpTemperature,_tmpHumidity,_tmpNotes,_tmpInspectionDate);
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
  public LiveData<Integer> getInspectionCount() {
    final String _sql = "SELECT COUNT(*) FROM inspections";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"inspections"}, false, new Callable<Integer>() {
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

  @Override
  public LiveData<Integer> getLowActivityCount() {
    final String _sql = "SELECT COUNT(*) FROM inspections WHERE activityLevel = 'Low'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"inspections"}, false, new Callable<Integer>() {
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
