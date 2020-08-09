package cn.edu.scujcc.workthreeweek.test;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import cn.edu.scujcc.workthreeweek.data.local.MyDBHelper;

public class MyContentProvider extends ContentProvider {
    private static final String TAG = "MyContentProvider";
    private static final int OK = 1;
    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI("cn.edu.scujcc.workthreeweek", "test", OK);
    }

    private MyDBHelper dbHelper;
    private String tableName = "userInfo";

    @Override
    public boolean onCreate() {
        dbHelper = new MyDBHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        if (uriMatcher.match(uri) == OK) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            cursor = db.query(tableName, projection, selection, selectionArgs, null, null, null);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.e(TAG, "insert: " + values.getAsString("name") + "," + values.getAsString("author"));
        if (uriMatcher.match(uri) == OK) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.insert(tableName, null, values);
            db.close();
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == OK) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete(tableName, selection, selectionArgs);
            db.close();
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (uriMatcher.match(uri) == OK) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //values 新内容
            //selection 判断语句 name=？
            //selectionArgs 条件判断实体内容参数
            db.update(tableName, values, selection, selectionArgs);
            db.close();
        }
        return 0;
    }
}


