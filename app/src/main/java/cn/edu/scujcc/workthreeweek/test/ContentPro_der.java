package cn.edu.scujcc.workthreeweek.test;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import cn.edu.scujcc.workthreeweek.data.local.DBHelper;
import cn.edu.scujcc.workthreeweek.util.ContentUtils;

/**
 * <pre>
 *     author : Administrator
 *     e-mail : xxx@xx
 *     time   : 2020/08/09
 *     desc   :
 *     version: 1.0
 * </pre>
 *
 * @author Administrator
 */

public class ContentPro_der extends ContentProvider {

    private final static UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(ContentUtils.AUTOHORITY, "book", 1);
        mMatcher.addURI(ContentUtils.AUTOHORITY, "book1/#", 2);
    }

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {

        dbHelper = new DBHelper(this.getContext(), "BOOKDB", null, 1);
        return true;
        // TODO Auto-generated method stub
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
        // TODO Auto-generated method stub

    }

    @Override
    public String getType(Uri uri) {
        return null;
        // TODO Auto-generated method stub

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return uri;
        // TODO Auto-generated method stub

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        switch (mMatcher.match(uri)) {
            case 1:
                db = dbHelper.getWritableDatabase();
                db.update("book", values, selection, selectionArgs);
                break;
            default:
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return 0;
    }
}

