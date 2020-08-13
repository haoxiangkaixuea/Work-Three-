package cn.edu.scujcc.workthreeweek;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import cn.edu.scujcc.workthreeweek.data.local.BookDBHelper;

/**
 * @author Administrator
 */

public class DataContentProvider extends ContentProvider {
    public static final String AUTHORITY = "cn.edu.scujcc.workthreeweek";
    public static final int BOOK_CODE = 0;
    public static final int BOOKS_CODE = 2;
    /**
     * URI_MATCHER:在ContentProvider 中注册URI
     */
    private static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, "book", BOOK_CODE);
        URI_MATCHER.addURI(AUTHORITY, "books", BOOKS_CODE);
    }

    private BookDBHelper bookDbHelper;
    private SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        bookDbHelper = new BookDBHelper(getContext(), "BookDBHelper,db", null, 1);
        return true;
    }

    /**
     * 删除数据
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db = bookDbHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (URI_MATCHER.match(uri)) {
            case BOOK_CODE:
                deleteRows = db.delete("Book", selection, selectionArgs);
                break;
            case BOOKS_CODE:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = db.delete("Book", "id=?", new String[]{bookId});
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * 插入数据
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = bookDbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (URI_MATCHER.match(uri)) {
            case BOOK_CODE:
            case BOOKS_CODE:
                long newBookId = db.insert("Book", null, values);
                uriReturn = Uri.parse("content://" + AUTHORITY + "/book/" + newBookId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    /**
     * 查询数据
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        db = bookDbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (URI_MATCHER.match(uri)) {
            case BOOK_CODE:
                cursor = db.query("Book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOKS_CODE:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("Book", projection, "id=?", new String[]{bookId}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    /**
     * 更新数据
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        db = bookDbHelper.getWritableDatabase();
        int updateRows = 0;
        switch (URI_MATCHER.match(uri)) {
            case BOOK_CODE:
                updateRows = db.update("book", values, selection, selectionArgs);
                break;
            case BOOKS_CODE:
                String bookId = uri.getPathSegments().get(1);
                updateRows = db.update("book", values, "id=?", new String[]{bookId});
                break;
            default:
                break;
        }
        return updateRows;
    }
}

