package cn.edu.scujcc.workthreeweek.no;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
public class MyContent extends ContentProvider {

    /**
     * 设置ContentProvider的唯一标识 AndroidManifest.xml配置
     */
    public static final String AUTOHORITY = "cn.edu.scujcc.workthreeweek";
    public static final int BOOK_CODE = 1;
    public static final int CATEGORY_CODE = 2;
    /**
     * UriMatcher类使用:在ContentProvider 中注册URI
     */
    private static final UriMatcher M_MATCHER;

    // 初始化
    static {
        M_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        // 若URI资源路径 = content://com.wjn.mycontentprovide/user ，则返回注册码User_Code
        M_MATCHER.addURI(AUTOHORITY, "Book", BOOK_CODE);
        // 若URI资源路径 = content://com.wjn.mycontentprovide/job ，则返回注册码Job_Code
        M_MATCHER.addURI(AUTOHORITY, "category", CATEGORY_CODE);
    }

    private Context mContext;
    private SQLiteDatabase db = null;

    /**
     * onCreate方法
     */

    @Override
    public boolean onCreate() {
        mContext = getContext();
        MyDBHelper mDbHelper = new MyDBHelper(getContext(), "BookStore.db", null, 1);
        db = mDbHelper.getReadableDatabase();
        //删除user表中所有信息 可以做别的操作
        db.execSQL("delete from Book");
        //删除job表中所有信息 可以做别的操作
        db.execSQL("delete from Categroy");
        return true;
    }

    /**
     * 增
     */

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        String table = getTableName(uri);
        // 向该表添加数据
        db.insert(table, null, values);
        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    /**
     * 删
     */

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        String table = getTableName(uri);
        return db.delete(table, selection, selectionArgs);
    }

    /**
     * 改
     */

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        String table = getTableName(uri);
        return db.update(table, values, selection, selectionArgs);
    }

    /**
     * 查
     */

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        String table = getTableName(uri);
        // 查询数据
        return db.query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    /**
     * getType方法 获取数据类型
     */

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        // 由于不展示,此处不作展开
        return null;
    }

    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名   
     */

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (M_MATCHER.match(uri)) {
            case BOOK_CODE:
                tableName = "book";
                break;
            case CATEGORY_CODE:
                tableName = "category";
                break;
            default:
        }
        return tableName;
    }
}