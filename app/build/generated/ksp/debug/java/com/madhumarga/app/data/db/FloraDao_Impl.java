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
import com.madhumarga.app.data.model.Flora;
import java.lang.Class;
import java.lang.Exception;
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
public final class FloraDao_Impl implements FloraDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Flora> __insertionAdapterOfFlora;

  private final EntityDeletionOrUpdateAdapter<Flora> __deletionAdapterOfFlora;

  public FloraDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFlora = new EntityInsertionAdapter<Flora>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `flora` (`id`,`flowerName`,`bloomingSeason`,`nectarRating`,`distanceKm`,`location`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Flora entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getFlowerName());
        statement.bindString(3, entity.getBloomingSeason());
        statement.bindLong(4, entity.getNectarRating());
        statement.bindDouble(5, entity.getDistanceKm());
        statement.bindString(6, entity.getLocation());
        statement.bindString(7, entity.getNotes());
      }
    };
    this.__deletionAdapterOfFlora = new EntityDeletionOrUpdateAdapter<Flora>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `flora` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Flora entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertFlora(final Flora flora, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFlora.insert(flora);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFlora(final Flora flora, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFlora.handle(flora);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Flora>> getAllFlora() {
    final String _sql = "SELECT * FROM flora ORDER BY flowerName ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"flora"}, false, new Callable<List<Flora>>() {
      @Override
      @Nullable
      public List<Flora> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFlowerName = CursorUtil.getColumnIndexOrThrow(_cursor, "flowerName");
          final int _cursorIndexOfBloomingSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "bloomingSeason");
          final int _cursorIndexOfNectarRating = CursorUtil.getColumnIndexOrThrow(_cursor, "nectarRating");
          final int _cursorIndexOfDistanceKm = CursorUtil.getColumnIndexOrThrow(_cursor, "distanceKm");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<Flora> _result = new ArrayList<Flora>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Flora _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFlowerName;
            _tmpFlowerName = _cursor.getString(_cursorIndexOfFlowerName);
            final String _tmpBloomingSeason;
            _tmpBloomingSeason = _cursor.getString(_cursorIndexOfBloomingSeason);
            final int _tmpNectarRating;
            _tmpNectarRating = _cursor.getInt(_cursorIndexOfNectarRating);
            final float _tmpDistanceKm;
            _tmpDistanceKm = _cursor.getFloat(_cursorIndexOfDistanceKm);
            final String _tmpLocation;
            _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            final String _tmpNotes;
            _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            _item = new Flora(_tmpId,_tmpFlowerName,_tmpBloomingSeason,_tmpNectarRating,_tmpDistanceKm,_tmpLocation,_tmpNotes);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
