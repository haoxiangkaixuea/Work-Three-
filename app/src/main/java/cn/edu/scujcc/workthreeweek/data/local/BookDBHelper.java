package cn.edu.scujcc.workthreeweek.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author Administrator
 */
public class BookDBHelper extends SQLiteOpenHelper {
    /**
     * 表名
     */
    public static final String BOOK_TABLE_NAME = "book";
    /**
     * 把数据库创建定义为一个字符串常量
     * 创建图书数据库
     */
    public static final String CREATE_BOOK = "create table if not exists Book("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text)";
    /**
     * 数据库名
     */
    private static final String DATABASE_NAME = "demo.db";
    /**
     * 数据库版本号
     */
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public BookDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
