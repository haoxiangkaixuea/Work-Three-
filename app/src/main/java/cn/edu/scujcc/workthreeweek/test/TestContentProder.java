package cn.edu.scujcc.workthreeweek.test;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import cn.edu.scujcc.workthreeweek.data.local.MyDBHelper;

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

public class TestContentProder extends ContentProvider {
    public static final String AUTOHORITY = "cn.edu.scujcc.workthreeweek";
    public static final int BOOK_CODE = 0;
    public static final int CATEGORY_CODE = 1;
    public static final int BOOKS_CODE = 2;
    public static final int CATEGORYS_CODE = 3;
    // UriMatcher类使用:在ContentProvider 中注册URI
    private static final UriMatcher uriMatcher;
    // 设置ContentProvider的唯一标识

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 初始化
        uriMatcher.addURI(AUTOHORITY, "book", BOOK_CODE);
        uriMatcher.addURI(AUTOHORITY, "category", CATEGORY_CODE);
        uriMatcher.addURI(AUTOHORITY, "books", BOOKS_CODE);
        uriMatcher.addURI(AUTOHORITY, "categorys", CATEGORYS_CODE);
        // 若URI资源路径 = content://cn.scu.myprovider/user ，则返回注册码User_Code
        // 若URI资源路径 = content://cn.scu.myprovider/job ，则返回注册码Job_Code
    }

    private MyDBHelper dbHelper;
    private SQLiteDatabase db;
    private Context mContext;

    @Override
    public boolean onCreate() {
        mContext = getContext();
        dbHelper = new MyDBHelper(getContext());
        return true;
    }

    /**
     * 删除数据
     *
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_CODE:
                deleteRows = db.delete("book", selection, selectionArgs);
                break;
            case BOOKS_CODE:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = db.delete("book", "id=?", new String[]{bookId});
                break;
            case CATEGORY_CODE:
                deleteRows = db.delete("category", selection, selectionArgs);
                break;
            case CATEGORYS_CODE:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = db.delete("book", "id=?", new String[]{categoryId});
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
     *
     * @param uri
     * @param values
     * @return
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        db = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_CODE:
            case BOOKS_CODE:
                long newBookId = db.insert("book", null, values);
                uriReturn = Uri.parse("cotent://" + AUTOHORITY + "/book/" + newBookId);
                break;
            case CATEGORY_CODE:
            case CATEGORYS_CODE:
                long newCategoryId = db.insert("book", null, values);
                uriReturn = Uri.parse("cotent://" + AUTOHORITY + "/category/" + newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    /**
     * 查询数据
     *
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case BOOK_CODE:
                cursor = db.query("book", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case BOOKS_CODE:
                String bookId = uri.getPathSegments().get(1);
                cursor = db.query("book", projection, "id=?", new String[]{bookId}, null, null, sortOrder);
                break;
            case CATEGORY_CODE:
                cursor = db.query("category", projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CATEGORYS_CODE:
                String categoryId = uri.getPathSegments().get(1);
                cursor = db.query("category", projection, "id=?", new String[]{categoryId}, null, null, sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    /**
     * 更新数据
     *
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        db = dbHelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)) {
            case BOOK_CODE:
                updateRows = db.update("book", values, selection, selectionArgs);
                break;
            case BOOKS_CODE:
                String bookId = uri.getPathSegments().get(1);
                updateRows = db.update("book", values, "id=?", new String[]{bookId});
                break;
            case CATEGORY_CODE:
                updateRows = db.update("category", values, selection, selectionArgs);
                break;
            case CATEGORYS_CODE:
                String categoryId = uri.getPathSegments().get(1);
                updateRows = db.update("book", values, "id=?", new String[]{categoryId});
                break;
            default:
                break;
        }
        return updateRows;
    }
}

