package cn.edu.scujcc.workthreeweek.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author Administrator
 */
public class CategoryDBHelper extends SQLiteOpenHelper {
    /**
     * 创建图书类别数据库
     * CREATE_CATEGORY 图书类别数据库
     */
    public static final String CREATE_CATEGORY = "create table if not exists Category("
            + "id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer)";

    public CategoryDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_CATEGORY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
